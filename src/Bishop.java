/*
Напишите класс Bishop (слон). Этот класс должен быть наследником от класса ChessPiece, который вы сделали в предыдущей задаче.

В классе Bishop:

        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.

        Реализуйте метод canMoveToPosition() так, чтобы слон не мог выйти за доску
        (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0)
        и мог ходить только по диагонали, также фигура не может сходить в точку, в которой она сейчас находится.
        Если слон может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
        то функция вернет true, иначе — false.

        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае слон —  B.

Также вы можете добавить и свои методы для удобства.
*/
/*
Допишите методы canMoveToPosition (у всех фигур) так,
чтобы фигура не могла проходить через другую (для этого мы передаем в этот метод ChessBoard, у которого есть публичная переменная board).
Также давайте сделаем так, чтобы фигуры могли есть друг друга. То есть, если мы двигаем белую фигуру на поле,
на котором стоит черная, то черная фигура просто съедается (съедание фигуры уже реализовано в классе ChessBoard,
вам надо сделать только так, чтобы метод canMoveToPosition() возвращал true при таком раскладе).
*/
public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return isInBounds(line, column, toLine, toColumn) &&
                !isSamePosition(line, column, toLine, toColumn) &&
                isDiagonalMove(line, column, toLine, toColumn) &&
                canEatFigure(chessBoard, line, column, toLine, toColumn);
    }

    private boolean isInBounds(int line, int column, int toLine, int toColumn) {
        return line >= 0 && line < 8 && column >= 0 && column < 8 &&
                toLine >= 0 && toLine < 8 && toColumn >= 0 && toColumn < 8;
    }

    private boolean isSamePosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    private boolean isDiagonalMove(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
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
