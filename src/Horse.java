/*
Теперь напишите класс Horse (конь). Этот класс должен быть наследником класса ChessPiece, который вы сделали в предыдущей задаче.

В классе Horse:

        Реализуйте конструктор, который будет принимать лишь цвет фигуры.
        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.

        Реализуйте метод canMoveToPosition() так,
        чтобы конь не мог выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8,
        напоминаем, что индексы начинаются с 0) и мог ходить только буквой «Г». Также фигура не может сходить в точку,
        в которой она сейчас находится. Если конь может пройти от точки (line, column) до точки (toLine, toColumn)
        по всем правилам (указанным выше), то функция вернет true, иначе — false.

        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае конь — это  H.

Также вы можете добавить и свои методы для удобства.
*/
public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return isInBounds(line, column, toLine, toColumn) &&
                !isSamePosition(line, column, toLine, toColumn) &&
                isValidMove(line, column, toLine, toColumn) &&
                canEatFigure(chessBoard, line, column, toLine, toColumn);
    }

    private boolean isInBounds(int line, int column, int toLine, int toColumn) {
        return line >= 0 && line < 8 && column >= 0 && column < 8 &&
                toLine >= 0 && toLine < 8 && toColumn >= 0 && toColumn < 8;
    }

    private boolean isSamePosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    private boolean isValidMove(int line, int column, int toLine, int toColumn) {
        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
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
