package ten;


import java.util.List;

public class MazePoint {
    private Point point;
    private PipeSymbol pipeSymbol=PipeSymbol.GROUND;
    private  int numericValue=-1;

    public MazePoint(int row, int column,char symbol) {
        this.point=new Point(row,column);
        this.pipeSymbol = PipeSymbol.identifyPipeSymbol(symbol);

    }

    public Point getPoint() {

        return point;
    }



    public int getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(int numericValue) {
        this.numericValue = numericValue;
    }

    public PipeSymbol getPipeSymbol() {
        return pipeSymbol;
    }

    public void setPipeSymbol(char symbol) {

        this.pipeSymbol = PipeSymbol.valueOf(String.valueOf(symbol));
    }

    public void PrintSymbolValue()
    {
        System.out.print(pipeSymbol);
    }
    public void PrintNumericValue()
    {
        System.out.format("%03d", this.numericValue);
        //System.out.print();
    }

    public List<Point> getConnectedMazePoint() {
        return this.pipeSymbol.ConnectedPoint(this.point);
    }
}