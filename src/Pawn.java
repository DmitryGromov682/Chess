/*
Задача 3

Создайте класс Pawn (пешка), который так же, как и Horse, должен быть наследником класса ChessPiece.

Реализуйте в классе Pawn, всё тоже самое, что и в классе Horse.

В классе Pawn:

        Реализуйте конструктор, который будет принимать цвет фигуры.
        Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.

        Реализуйте метод canMoveToPosition() так, чтобы пешка не могла выйти за доску и могла ходить только вперед.
        Помните, что первый ход пешка может сдвинуться на 2 поля вперед, сделать это можно, например, сравнив координаты.
        То есть, если пешка белая (color.equals("White")) и находится в line == 1, то она может пойти на 2 поля вперед,
        иначе — нет, аналогично с черными пешками. Также фигура не может сходить в точку, в которой она сейчас находится.
        Если пешка может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
        то функция вернет true, иначе — false.

        Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае пешка — это P.

Также вы можете добавить и свои методы для удобства.
*/
public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "P";
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
        if (color.equals("White")) {
            if (line == 1) {
                return toLine - line == 2 && column == toColumn || toLine - line == 1 && column == toColumn;
            } else {
                return toLine - line == 1 && column == toColumn;
            }
        } else {
            if (line == 6) {
                return line - toLine == 2 && column == toColumn || line - toLine == 1 && column == toColumn;
            } else {
                return line - toLine == 1 && column == toColumn;
            }
        }
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


