package parser;

import SimpleScanner.Scanner;
import java.io.File;
import SyntaxTree.*;
import SyntaxTree.ProgramNode;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by LinaVo on 4/10/16.
 */
public class ParserTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("tree.txt", "UTF-8");

        Parser textFile = new Parser("/Users/LinaVo/vo-compiler2/vo-compiler/testing/parser/test-syntax-tree-integration.pas");
        
        ProgramNode program = textFile.program();
        writer.println("\n\n\nindented syntaxTree shown below \n---------------------------------------\n");
        writer.println(program.indentedToString(0));
        writer.close();
    }
}



