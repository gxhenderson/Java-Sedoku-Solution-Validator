import java.util.Arrays;

/**
 * SedokuSolutionValidator takes a completely
 * answered Sedoku puzzle and checks that the
 * solution is accurate
 *
 * @author Gerald Henderson, Charlie Ligget,
 * Phil Essenmacher
 * 
 * @version 8.0.2 
 */
public class SedokuSoloutionValidator 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /** 
         * create two dimensional array for 
         * puzzle solution
         */
        int[][] puzzle =
        {
            {6,2,4,5,3,9,1,8,7},
            {5,1,9,7,2,8,6,3,4},
            {8,3,7,6,1,4,2,9,5},
            {1,4,3,8,6,5,7,2,9},
            {9,5,8,2,4,7,3,6,1},
            {7,6,2,3,9,1,4,5,8},
            {3,7,1,9,5,6,8,4,2},
            {4,9,6,1,8,2,5,7,3},
            {2,8,5,4,7,3,9,1,6}
        };
        
        /* create tasks */
        Row row = new Row(puzzle);
        Column column = new Column(puzzle);
        Square square1 = new Square(puzzle, 0, 0);
        Square square2 = new Square(puzzle, 0, 3);
        Square square3 = new Square(puzzle, 0, 6);
        Square square4 = new Square(puzzle, 3, 0);
        Square square5 = new Square(puzzle, 3, 3);
        Square square6 = new Square(puzzle, 3, 6);
        Square square7 = new Square(puzzle, 6, 0);
        Square square8 = new Square(puzzle, 6, 3);
        Square square9 = new Square(puzzle, 6, 6);
        
        
        
        /** create threads */
        Thread rowThread = new Thread(row);
        Thread columnThread = new Thread(column);
        Thread squareThread1 = new Thread(square1);
        Thread squareThread2 = new Thread(square2);
        Thread squareThread3 = new Thread(square3);
        Thread squareThread4 = new Thread(square4);
        Thread squareThread5 = new Thread(square5);
        Thread squareThread6 = new Thread(square6);
        Thread squareThread7 = new Thread(square7);
        Thread squareThread8 = new Thread(square8);
        Thread squareThread9 = new Thread(square9);
        
        /** start threads */
        rowThread.start();
        columnThread.start();
        squareThread1.start();
        squareThread2.start();
        squareThread3.start();
        squareThread4.start();
        squareThread5.start();
        squareThread6.start();
        squareThread7.start();
        squareThread8.start();
        squareThread9.start();
        
        /* 
          If each row, column, and square is a solution,
          print valid, otherwise print invalid
        */
        if(row.IsSolution() && column.IsSolution() && 
           square1.IsSolution() && square2.IsSolution() &&
           square3.IsSolution() && square4.IsSolution() &&
           square5.IsSolution() && square6.IsSolution() &&
           square7.IsSolution() && square8.IsSolution() &&
           square9.IsSolution())
            System.out.print("The solution is valid!\n");
        else
            System.err.print("The solution is NOT valid!\n");
    }    
}

/** 
 * Creates a thread that looks through
 * each row for the numbers 1 - 9
 */
class Row implements Runnable
{
  private int[][] solution;
  private static boolean valid = true;
  
  /**
   * Initialize the column
   * 
   * @param puzzle 
   */
  public Row(int[][] puzzle)
  {
      solution = puzzle;
  }
  
  @Override /** Override the run() method*/
  /** Checks each row for the numbers 1 - 9 */
  public void run()
  {
      /* 
        temp array that stores the elements
        in the row
      */
      int[] temp = new int[9];
      
      /* clycle through each row */
      for(int i = 0; i < 9; i++)
      {
          /* put the row into the temp variable */
          for(int j = 0; j < 9; j++)
          {
              temp[j] = solution[i][j];
          }

          /* Check that the row is a valid solution */
          if(!(CheckValid(temp)))
             valid = false;
          else
              valid = true;
      }
  }
  
  /** 
   * Sorts the array and checks that it contains
   * the values 1 - 9 for a valid solution
   * 
   * @param temp
   * @return boolean
   */
  private boolean CheckValid(int[] temp)
  {
      Arrays.sort(temp);
      
      /* Checks that the array has only/all numbers 1 - 9 */
      for(int i = 0; i < 9; i++)
      {
          if(!(temp[i] == i+1))
              return false;
      }
      return true;
  }
  
  /**
   * Returns whether the row is valid or invalid
   * 
   * @return valid
   */
  public boolean IsSolution()
  {
      return valid;
  }
}

/** 
 * Creates a thread that looks through
 * each column for the numbers 1 - 9
 */
class Column implements Runnable
{
  private int[][] solution;
  private static boolean valid = true;
  
  /**
   * Initialize the column
   * 
   * @param puzzle 
   */
  public Column(int[][] puzzle)
  {
      solution = puzzle;
  }
  
  @Override /** Override the run() method*/
  /** Checks each row for the numbers 1 - 9 */
  public void run()
  {
      /* 
        temp array that stores the elements
        in the column
      */
      int[] temp = new int[9];
      
      /* Cycle through each element in the column */
      for(int i = 0; i < 9; i++)
      {
          /* put the column in the temp array */
          for(int j = 0; j < 9; j++)
          {
              temp[j] = solution[j][i];
          }

          /* Check that the column is a valid solution */
          if(!(CheckValid(temp)))
             valid = false;
          else
              valid = true;
      }
  }
  
  /**
   * Sorts the array and checks that it contains
   * the values 1 = 9 for a valid solution
   * 
   * @param temp
   * @return boolean
   */
  private boolean CheckValid(int[] temp)
  {
      Arrays.sort(temp);
      
      /* Checks that the array has only/all numbers 1 - 9 */
      for(int i = 0; i < 9; i++)
      {
          if(!(temp[i] == i+1))
              return false;
      }
      return true;
  }
  
  /**
   * Returns whether the row is valid or invalid
   * 
   * @return valid 
   */
  public boolean IsSolution()
  {
      return valid;
  }
}

/** 
 * Creates a thread that looks through
 * each square for the numbers 1 - 9
 * 
 */
class Square implements Runnable
{
  private int[][] solution;
  private static boolean valid = true;
  private int rowIndex;
  private int columnIndex;
  
  /**
   * Initializes the square
   * 
   * @param puzzle
   * @param row
   * @param column 
   */
  public Square(int[][] puzzle, int row, int column)
  {
      solution = puzzle;
      rowIndex = row;
      columnIndex = column;
  }
  
  @Override /** Override the run() method*/
  /** Checks each row for the numbers 1 - 9 */
  public void run()
  {
      /* 
        temp array that stores the elements
        in the square
      */
      int[] temp = new int[9];
      
      /*
        Reads in a square by reading in three
        elements in three sequential rows
      */
      for(int i = rowIndex; i < rowIndex + 3; i++)
      {
          for(int j = columnIndex; j < columnIndex + 3; j++)
          {
              temp[j] = solution[i][j];
          }
      }
      
      /* Check that the column is a valid solution */
      if(!(CheckValid(temp)))
          valid = false;
      else
          valid = true;
  }
  
  /**
   * Sorts the array and checks that it contains
   * the values 1 = 9 for a valid solution
   * 
   * @param temp
   * @return boolean
   */
  private boolean CheckValid(int[] temp)
  {
      Arrays.sort(temp);
      
      /* Checks that the array has only/all numbers 1 - 9 */
      for(int i = 0; i < 9; i++)
      {
          if(!(temp[i] == i+1))
              return false;
      }
      return true;
  }
  
  /**
   * Returns whether the row is valid or invalid
   * 
   * @return valid 
   */
  public boolean IsSolution()
  {
      return valid;
  }
}

