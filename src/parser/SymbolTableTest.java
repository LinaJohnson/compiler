package parser;
import SimpleScanner.TokenType;
import SimpleScanner.LookupTable;
import SimpleScanner.Scanner;
import static SimpleScanner.TokenType.PROGRAM;
//import org.junit.*;
import parser.SymbolTable.Kind;
/**
 * @author LinaVo
 * This is the test for the SymbolTable java class.
 * I'm using junit here.
 */
public class SymbolTableTest {
    /**
     * Main for SymbolTable tests.
     * @param args 
     *
    public static void main(String[] args) {
        SymbolTable testTable = new SymbolTable();
        testPushTable(testTable);
        testPopTable(testTable);
        testAddandExists(testTable);
        testToString(testTable);
    }
    @Test
    public static void testPushTable(SymbolTable testTable) {
        SymbolTable emptyTable = new SymbolTable();
        org.junit.Assert.assertSame("the stacks are not empty, they should be", emptyTable.getTableSize(), testTable.getTableSize());
        testTable.pushTable();
        org.junit.Assert.assertNotSame("Error - hashtable not added to scope stack", emptyTable.getTableSize(), testTable.getTableSize());
    }
    @Test
    public static void testPopTable(SymbolTable testTable) {
        SymbolTable emptyTable = new SymbolTable();
        org.junit.Assert.assertNotSame("stacks should already have something on them", emptyTable.getTableSize(), testTable.getTableSize());
        testTable.popTable();
        org.junit.Assert.assertSame("stacks should both be empty", emptyTable.getTableSize(), testTable.getTableSize());
    }
    @Test
    public static void testAddandExists(SymbolTable testTable) {
        //testing both functions add and exsits
        SymbolTable emptyTable = new SymbolTable();
        org.junit.Assert.assertSame("stacks should both be empty", emptyTable.getTableSize(), testTable.getTableSize());
        testTable.add("foo", Kind.PROGRAM);
        org.junit.Assert.assertNotSame("lexeme wasn't added", emptyTable.exists("foo"), null);
    }
    @Test
    public static void testGetKind(SymbolTable testTable) {
        org.junit.Assert.assertSame("kind was not the same", testTable.getKind("foo"), "foo");
    }
    @Test
    public static void testToString(SymbolTable testTable) {
        System.out.println(testTable.toString());
    }

    */
}
