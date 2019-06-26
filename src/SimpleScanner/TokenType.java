package SimpleScanner;

/**
 *
 * This represents the various tokens in the mini Pascal language.
 * @author Lina Vo
 * 
 */
public enum TokenType {
    
    // Keyword Token Types
    ID,
    PROGRAM,
    WHILE,
    OR,
    AND,
    MOD,
    IF,
    ELSE,
    VAR,
    DIV,
    INTEGER,
    ARRAY,
    OF,
    REAL,
    FUNCTION,
    PROCEDURE,
    BEGIN,
    END,
    THEN,
    DO,
    NOT,
    NUM,
    
    // Symbol Token Types
    EQUALS,
    PLUS,
    MINUS,
    GREATER_THAN,
    GREATER_THAN_EQUALS,
    LESS_THAN,
    LESS_THAN_EQUALS,
    LESS_THAN_GREATER_THAN,
    MULTIPLY,
    DIVIDE,
    ASSIGN,
    OPEN_COMMENT,
    CLOSE_COMMENT,
    OPEN_PAREN,
    CLOSE_PAREN,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    SEMICOLON,
    COLON,
    PERIOD,
    EXPONENT,
    COMMA,
}
