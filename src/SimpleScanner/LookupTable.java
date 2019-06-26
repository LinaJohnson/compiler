package SimpleScanner;

import java.util.HashMap;

/**
 *
 * This is the lookup table for the tokens within the mini Pascal language.
 * @author LinaVo
 * 
 */
public class LookupTable extends HashMap<String,TokenType>{
    
    public LookupTable() {
        // Place Keywords on the map
        this.put("program", TokenType.PROGRAM);
        this.put("div", TokenType.DIV);
        this.put("while", TokenType.WHILE);
        this.put("or", TokenType.OR);
        this.put("and", TokenType.AND);
        this.put("mod", TokenType.MOD);
        this.put("if", TokenType.IF);
        this.put("var", TokenType.VAR);
        this.put("integer", TokenType.INTEGER);
        this.put("array", TokenType.ARRAY);
        this.put("of", TokenType.OF);
        this.put("real", TokenType.REAL);
        this.put("function", TokenType.FUNCTION);
        this.put("procedure", TokenType.PROCEDURE);
        this.put("begin", TokenType.BEGIN);
        this.put("end", TokenType.END);
        this.put("then", TokenType.THEN);
        this.put("else", TokenType.ELSE);
        this.put("do", TokenType.DO);
        this.put("not", TokenType.NOT);
        this.put("num", TokenType.NUM);
        
        // Place Symbols on the map
        this.put("+", TokenType.PLUS);
        this.put("-", TokenType.MINUS);
        this.put("=", TokenType.EQUALS);
        this.put(">", TokenType.GREATER_THAN);
        this.put(">=", TokenType.GREATER_THAN_EQUALS);
        this.put("<", TokenType.LESS_THAN);
        this.put("<=", TokenType.LESS_THAN_EQUALS);
        this.put("<>", TokenType.LESS_THAN_GREATER_THAN);
        this.put("*", TokenType.MULTIPLY);
        this.put("/", TokenType.DIVIDE);
        this.put(":=", TokenType.ASSIGN);
        this.put("{", TokenType.OPEN_COMMENT);
        this.put("}", TokenType.CLOSE_COMMENT);
        this.put("(", TokenType.OPEN_PAREN);
        this.put(")", TokenType.CLOSE_PAREN);
        this.put("[", TokenType.OPEN_BRACKET);
        this.put("]", TokenType.CLOSE_BRACKET);
        this.put(";", TokenType.SEMICOLON);
        this.put(":", TokenType.COLON);
        this.put(".", TokenType.PERIOD);
        this.put("E", TokenType.EXPONENT);
        this.put(",", TokenType.COMMA);
    }
}
