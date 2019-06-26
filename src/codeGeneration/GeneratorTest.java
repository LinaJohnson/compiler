
package codeGeneration;
import parser.*;
import SyntaxTree.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/**
 *
 * @author LinaVo
 */
public class GeneratorTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        if(args.length != 0) {
            String filename = args[0];
            Generator code = new Generator(filename);
            PrintWriter writer = null;
            if(args.length == 2) {
                // allow user to choose what to name the output file
                writer = new PrintWriter(args[1], "UTF-8");
            }
            else {
                writer = new PrintWriter(args[0].replace(".pas",".asm"), "UTF-8");
            }
            writer.println(code.generate());
            writer.close();
        }
        else {
            System.out.println("Usage: java -jar vo-compiler.jar <yourfilename.pas> [outputfile.asm]");
            System.exit(-1);
        }
    }
}
