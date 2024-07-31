/*
Задача 6

Напишите класс Queen и класс King.

В этих классах:

        Реализуйте конструктор, который будет принимать лишь цвет фигуры.
        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.

        Реализуйте метод canMoveToPosition() так, чтобы фигуры не могли выйти за доску
        (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0)
        и могли ходить так, как ходят в шахматах (Королева ходит и по диагонали и по прямой,
        Король — в любое поле вокруг себя),
        также фигура не может сходить в точку, в которой она сейчас находится.
        Если фигура может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
        то функция вернет true, иначе — false.

        Реализуйте метод getSymbol так, чтобы он возвращал строку — символ фигуры, для короля — K, для ферзя — Q.

Отдельно в классе King создайте метод isUnderAttack(ChessBoard board, int line, int column), возвращающий логическое (boolean) значение, который  будет проверять, находится ли поле, на котором стоит король (или куда собирается пойти) под атакой. Если это так, то метод должен вернуть true, иначе — false. Это позволит нам проверять шахи.

Также вы можете добавить и свои методы для удобства.
*/
public class King extends ChessPiece{
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return isInBounds(line, column, toLine, toColumn) &&
                !isSamePosition(line, column, toLine, toColumn) &&
                isOneStepMove(line, column, toLine, toColumn) &&
                canEatFigure(chessBoard, line, column, toLine, toColumn);
    }

    private boolean isInBounds(int line, int column, int toLine, int toColumn) {
        return line >= 0 && line < 8 && column >= 0 && column < 8 &&
                toLine >= 0 && toLine < 8 && toColumn >= 0 && toColumn < 8;
    }

    private boolean isSamePosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    private boolean isOneStepMove(int line, int column, int toLine, int toColumn) {
        return Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1;
    }
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        if (checkPos(line) && checkPos(column)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (chessBoard.board[i][j] != null) {
                        if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return false;
    }
    public boolean canEatFigure(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);
        if (line != toLine && column != toColumn &&
                deltaX == deltaY && checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                chessBoard.board[line][column] != null) {
            if (!chessBoard.board[line][column].equals(this)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

}
