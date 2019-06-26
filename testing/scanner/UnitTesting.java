package SimpleScanner;
import java.io.File;

/**
 * Unit testing for the Scanner class.
 * @author Lina Vo
 **/
public class UnitTesting {
    /**
     * @param args the command line arguments
    **/
        public static void main(String[] args) {
        // Create a Scanner and point it at a file
        Scanner scanner = new Scanner( new File(args[0]));

        // Variable instantiation
        // Should ignore comment at the start
        // Token: ID Lexeme: myBankAccount
        // Testing ID without digits.
        boolean hasToken = scanner.nextToken();
        TokenType token = scanner.getToken();
        String lexeme = scanner.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for id";
        assert token == TokenType.ID : "No token type for id";
        assert lexeme.equals("int") : "No lexeme for id";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: INTEGER Lexeme: 5
        // Testing whole integers.
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for integer";
        assert token == TokenType.INTEGER : "No token type for integer";
        assert lexeme.equals("5") : "No lexeme for integer";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: SEMICOLON Lexeme: ;
        // Testing shot symbol semicolon.
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for semi-colon";
        assert token == TokenType.SEMICOLON : "No token type for semi-colon";
        assert lexeme.equals(";") : "No lexeme for semi-colon";
        System.out.println(token + "\t" + lexeme);
        
        // Token: ID Lexeme: program44
        // Testing ID with a digit.
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for id";
        assert token == TokenType.ID : "No token type for id";
        assert lexeme.equals("program44") : "No lexeme for id";
        System.out.println(token + "\t\t" + lexeme);
        
        /**********************/
        /** testing KEYWORDS **/
        /**********************/
        
        // Token: PROGRAM Lexeme: program
        // testing keyword program
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for program";
        assert token == TokenType.PROGRAM : "No token type for program";
        assert lexeme.equals("program") : "No lexeme for program";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: DIV Lexeme: div
        // testing keyword div
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for div";
        assert token == TokenType.DIV : "No token type for div";
        assert lexeme.equals("div") : "No lexeme for div";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: WHILE Lexeme: while
        // testing keyword while
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for while";
        assert token == TokenType.WHILE : "No token type for while";
        assert lexeme.equals("while") : "No lexeme for while";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: OR Lexeme: or
        // testing keyword or
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for or";
        assert token == TokenType.OR : "No token type for or";
        assert lexeme.equals("or") : "No lexeme for or";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: AND Lexeme: and
        // testing keyword and
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for and";
        assert token == TokenType.AND : "No token type for and";
        assert lexeme.equals("and") : "No lexeme for and";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: MOD Lexeme: mod
        // testing keyword mod
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for mod";
        assert token == TokenType.MOD : "No token type for mod";
        assert lexeme.equals("mod") : "No lexeme for mod";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: IF Lexeme: if
        // testing keyword if
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for if";
        assert token == TokenType.IF : "No token type for if";
        assert lexeme.equals("if") : "No lexeme for if";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: VAR Lexeme: var
        // testing keyword var
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for var";
        assert token == TokenType.VAR : "No token type for var";
        assert lexeme.equals("var") : "No lexeme for var";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: INTEGER Lexeme: integer
        // testing keyword integer
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for integer";
        assert token == TokenType.INTEGER : "No token type for INTEGER";
        assert lexeme.equals("integer") : "No lexeme for integer";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: ARRAY Lexeme: array
        // testing keyword array
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for array";
        assert token == TokenType.ARRAY : "No token type for ARRAY";
        assert lexeme.equals("array") : "No lexeme for array";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: OF Lexeme: of
        // testing keyword of
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for of";
        assert token == TokenType.OF : "No token type for OF";
        assert lexeme.equals("of") : "No lexeme for of";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: REAL Lexeme: real
        // testing keyword real
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for real";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("of") : "No lexeme for real";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: FUNCTION Lexeme: function
        // testing keyword function
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for function";
        assert token == TokenType.FUNCTION : "No token type for FUNCTION";
        assert lexeme.equals("function") : "No lexeme for function";
        System.out.println(token + "\t" + lexeme);
        
        // Token: PROCEDURE Lexeme: procedure
        // testing keyword procedure
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for procedure";
        assert token == TokenType.PROCEDURE : "No token type for PROCEDURE";
        assert lexeme.equals("procedure") : "No lexeme for procedure";
        System.out.println(token + "\t" + lexeme);
        
        // Token: BEGIN Lexeme: begin
        // testing keyword function
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for begin";
        assert token == TokenType.BEGIN : "No token type for BEGIN";
        assert lexeme.equals("begin") : "No lexeme for begin";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: END Lexeme: end
        // testing keyword end
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for end";
        assert token == TokenType.END : "No token type for END";
        assert lexeme.equals("end") : "No lexeme for end";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: THEN Lexeme: then
        // testing keyword then
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for then";
        assert token == TokenType.THEN : "No token type for THEN";
        assert lexeme.equals("then") : "No lexeme for then";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: ELSE Lexeme: else
        // testing keyword else
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for else";
        assert token == TokenType.ELSE : "No token type for ELSE";
        assert lexeme.equals("else") : "No lexeme for else";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: DO Lexeme: do
        // testing keyword do
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for do";
        assert token == TokenType.DO : "No token type for DO";
        assert lexeme.equals("do") : "No lexeme for do";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: NOT Lexeme: not
        // testing keyword not
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for not";
        assert token == TokenType.FUNCTION : "No token type for NOT";
        assert lexeme.equals("not") : "No lexeme for not";
        System.out.println(token + "\t\t" + lexeme);
        
        // Token: PROCEDURE Lexeme: procedure
        // testing keyword and symbol
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for procedure";
        assert token == TokenType.PROCEDURE : "No token type for PROCEDURE";
        assert lexeme.equals("procedure") : "No lexeme for procedure";
        System.out.println(token + "\t" + lexeme);
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for SEMICOLON";
        assert token == TokenType.SEMICOLON : "No token type for SEMICOLON";
        assert lexeme.equals(";") : "No lexeme for ;";
        System.out.println(token + "\t" + lexeme);
        
        // testing to see if scanner will recognize my*BankAccount
        // as three seperate components.
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for ID";
        assert token == TokenType.ID : "No token type for ID";
        assert lexeme.equals("my") : "No lexeme for my";
        System.out.println(token + "\t\t" + lexeme);
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for MULTIPLY";
        assert token == TokenType.MULTIPLY : "No token type for MULTIPLY";
        assert lexeme.equals("*") : "No lexeme for *";
        System.out.println(token + "\t" + lexeme);
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for ID";
        assert token == TokenType.ID : "No token type for ID";
        assert lexeme.equals("BankAccount") : "No lexeme for BankAccount";
        System.out.println(token + "\t\t" + lexeme);
        
        /**********************/
        /** testing NUMBERS  **/
        /**********************/
        
        // testing fraction
        // token: REAL lexeme: 1.22
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for real";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("1.22") : "No lexeme for 1.22";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing exponent
        // token: REAL lexeme: 1E10
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for REAL";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("1E10") : "No lexeme for 1E10";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing fraction with exponent
        // token: REAL lexeme: 5.20E10
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for REAL";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("5.20E10") : "No lexeme for 5.20E10";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing exponent with negative sign
        // token: REAL lexeme: 1E-10
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for REAL";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("1E-10") : "No lexeme for 1E-10";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing exponent with positive sign
        // token: REAL lexeme: 5E+10
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for REAL";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("5E+10") : "No lexeme for 5E+10";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing fraction with exponent with negative sign
        // token: REAL lexeme: 5E+10
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for REAL";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("1.20E-10") : "No lexeme for 1.20E-10";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing fraction with exponent with positive sign
        // token: REAL lexeme: 4.2E+6
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for REAL";
        assert token == TokenType.REAL : "No token type for REAL";
        assert lexeme.equals("4.2E+6") : "No lexeme for 4.2E+6";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing error for invalid exponent
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        
        // testing error for invalid exponent with sign
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        
        
        // testing short symbol <
        // token: LESS_THAN lexeme: <
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for LESS_THAN";
        assert token == TokenType.LESS_THAN : "No token type for LESS_THAN";
        assert lexeme.equals("<") : "No lexeme for <";
        System.out.println(token + "\t" + lexeme);
        
        // testing short symbol :
        // token: COLON lexeme: :
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for COLON";
        assert token == TokenType.COLON : "No token type for COLON";
        assert lexeme.equals(":") : "No lexeme for :";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing short symbol >
        // token: GREATER_THAN lexeme: >
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for GREATER_THAN";
        assert token == TokenType.GREATER_THAN : "No token type for GREATER_THAN";
        assert lexeme.equals(">") : "No lexeme for >";
        System.out.println(token + "\t" + lexeme);
        
        // testing short symbol =
        // token: ASSIGN lexeme: =
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for ASSIGN";
        assert token == TokenType.ASSIGN : "No token type for ASSIGN";
        assert lexeme.equals("=") : "No lexeme for =";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing short symbol +
        // token: PLUS lexeme: +
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for PLUS";
        assert token == TokenType.PLUS : "No token type for PLUS";
        assert lexeme.equals("+") : "No lexeme for +";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing short symbol -
        // token: MINUS lexeme: -
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for MINUS";
        assert token == TokenType.MINUS : "No token type for MINUS";
        assert lexeme.equals("-") : "No lexeme for -";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing short symbol [
        // token: OPEN_BRACKET lexeme: [
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for OPEN_BRACKET";
        assert token == TokenType.OPEN_BRACKET : "No token type for OPEN_BRACKET";
        assert lexeme.equals("[") : "No lexeme for [";
        System.out.println(token + "\t" + lexeme);
        
        // testing short symbol ]
        // token: CLOSE_BRACKET lexeme: ]
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for CLOSE_BRACKET";
        assert token == TokenType.CLOSE_BRACKET : "No token type for CLOSE_BRACKET";
        assert lexeme.equals("]") : "No lexeme for ]";
        System.out.println(token + "\t" + lexeme);
        
        // testing short symbol (
        // token: OPEN_PAREN lexeme: {
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for OPEN_PAREN";
        assert token == TokenType.OPEN_PAREN : "No token type for OPEN_PAREN";
        assert lexeme.equals("(") : "No lexeme for (";
        System.out.println(token + "\t" + lexeme);
        
        // testing short symbol )
        // token: CLOSE_PAREN lexeme: )
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for CLOSE_PAREN";
        assert token == TokenType.CLOSE_PAREN : "No token type for CLOSE_PAREN";
        assert lexeme.equals(")") : "No lexeme for )";
        System.out.println(token + "\t" + lexeme);
        
        // testing short symbol ,
        // token: COMMA lexeme: ,
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for COMMA";
        assert token == TokenType.MINUS : "No token type for COMMA";
        assert lexeme.equals(",") : "No lexeme for ,";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing short symbol .
        // token: PERIOD lexeme: .
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for PERIOD";
        assert token == TokenType.PERIOD : "No token type for PERIOD";
        assert lexeme.equals(".") : "No lexeme for .";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing short symbol /
        // token: DIVIDE lexeme: /
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for DIVIDE";
        assert token == TokenType.DIVIDE : "No token type for DIVIDE";
        assert lexeme.equals("/") : "No lexeme for /";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing symbol <=
        // token: LESS_THAN_EQUALS lexeme: <=
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for LESS_THAN_EQUALS";
        assert token == TokenType.LESS_THAN_EQUALS : "No token type for LESS_THAN_EQUALS";
        assert lexeme.equals("<=") : "No lexeme for <=";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing symbol <>
        // token: LESS_THAN_GREATER_THAN lexeme: <>
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for LESS_THAN_GREATER_THAN";
        assert token == TokenType.LESS_THAN_GREATER_THAN : "No token type for LESS_THAN_GREATER_THAN";
        assert lexeme.equals("<>") : "No lexeme for <>";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing symbol >=
        // token: GREATER_THAN_EQUALS lexeme: >=
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for GREATER_THAN_EQUALS";
        assert token == TokenType.GREATER_THAN_EQUALS : "No token type for GREATER_THAN_EQUALS";
        assert lexeme.equals(">=") : "No lexeme for >=";
        System.out.println(token + "\t\t" + lexeme);
        
        // testing symbol :=
        // token: ASSIGN_OP lexeme: :=
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        assert hasToken : "No token for ASSIGN_OP";
        assert token == TokenType.ASSIGN_OP : "No token type for ASSIGN_OP";
        assert lexeme.equals(":=") : "No lexeme for :=";
        System.out.println(token + "\t\t\t" + lexeme);
        
        /******************************/
        /** testing ILLEGAL COMMENTS  **/
        /******************************/
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        
        /******************************/
        /** testing ILLEGAL SYMBOLS  **/
        /******************************/
        
        // testing ^
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing &
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing %
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing #
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing $
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing @
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // !
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing ~
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing |
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing \
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing '
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing "
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        // testing ?
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        
        // testing illegal fraction 1.
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        
        // End of file 
        hasToken = scanner.nextToken();
        token = scanner.getToken();
        lexeme = scanner.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken == false : "No token for EOF";
        assert token == null : "No token type for EOF";
        assert lexeme == null : "No lexeme for EOF";
    }
}
