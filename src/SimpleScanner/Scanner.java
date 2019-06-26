package SimpleScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.lang.StringBuilder;

/**
 * The Scanner is used to read in lexemes from a text file.
 * @author LinaVo
 */
public class Scanner {
    
    // class constraints
    private final int START = 0;
    private final int ERROR = 100;
    
    // "states" for IDs
    private final int IN_ID_OR_KEYWORD = 1;
    private final int ID_OR_KEYWORD_COMPLETE = 101;
    
    // "states" for symbols
    private final int LESS_THAN = 3;
    private final int GREATER_THAN_COLON = 4;
    private final int SHORT_SYMBOL_COMPLETE = 102;
    private final int SYMBOL_COMPLETE = 103;
    
    // "states" for comments
    private final int COMMENT = 2;
    
    // "states" for numbers
    private final int DIGIT = 5;
    private final int PERIOD = 6;
    private final int OPTIONAL_FRACTION = 7;
    private final int EXPONENT = 8;
    private final int SIGN = 9;
    private final int OPTIONAL_EXPONENT = 10;
    private final int NUMBER_COMPLETE = 104;
    
    // Instance Variables
    private TokenType type;
    private String lexeme;
    private PushbackReader input;
    private LookupTable lookup = new LookupTable();
    
    /**
     * Creates a Scanner based on an input file.
     * @param inputFile The file to read.
     */
    public Scanner(File inputFile) {
        FileReader fileReader = null;
        // test to see if file was found.
        try {
            fileReader = new FileReader(inputFile);
        }
        catch(FileNotFoundException e) {
            System.out.println("Could not find file " + inputFile + ".");
            System.exit(-1);
        }
        this.input = new PushbackReader(fileReader);
    }
    
    /**
     * Looks for next token.
     * @return boolean
     */
    public boolean nextToken() {
        
        // Instance Variables
        int stateNumber = 0;
        StringBuilder currentLexeme = new StringBuilder();
        int currentCharacter = 0;
        
        // As long as we don't hit an error do the following.
        while (stateNumber < ERROR) {
            // see if you can read the file.
            try {
                currentCharacter = input.read();
            }
            catch(IOException ioe) {
                System.out.println("Could not read file.");
                System.exit(-1);
            }

            switch(stateNumber) {
                
                // start state
                case START:
                    // if you've reached the end of the file
                    if(currentCharacter == -1 ) {
                        // end of file
                        this.lexeme = null;
                        this.type = null;
                        
                        return false;
                    }
                    // if you encounter a letter.
                    // go to the IN_ID_OR_KEYWORD state
                    else if(Character.isLetter(currentCharacter)) {
                        // next token is a letter
                        stateNumber = IN_ID_OR_KEYWORD;
                        // using StringBuilder because it's more efficient.
                        // must convert from ASCII to char
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter white space
                    else if(Character.isWhitespace(currentCharacter)) {
                        // don't do anything.
                    }
                    // if you've encountered an open curly bracket
                    // go to the COMMENT state
                    else if(currentCharacter == '{') {
                        // next token is beginning of comment
                        stateNumber = COMMENT;
                        // no need to append because it's a comment.
                    }
                    // if you've encountered a digit
                    // go to the DIGIT state.
                    else if(Character.isDigit(currentCharacter)) {
                        // next token is a digit
                        stateNumber = DIGIT;
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you've encountered a less than symbol
                    // go to the LESS_THAN state
                    else if(currentCharacter == '<') {
                        // next token is less than symbol
                        stateNumber = LESS_THAN;
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you've encountered a greater than symbol or a colon
                    // go to the GREATER_THAN_COLON state
                    else if(currentCharacter == '>' || currentCharacter == ':') {
                        // next token is greater than symbol or colon
                        stateNumber = GREATER_THAN_COLON;
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you see anything else...
                    else {
                        // if your current character is a short symbol
                        // go to the SHORT_SYMBOL_COMPLETE state
                        switch(currentCharacter) {
                            // all short symbol cases
                            case '[':
                            case ']':
                            case ',':
                            case '.':
                            case '=':
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                            case '(':
                            case ')':
                            case ';':
                                stateNumber = SHORT_SYMBOL_COMPLETE;
                                currentLexeme.append(Character.toString((char) currentCharacter));
                                break;
                            // if you don't see any short symbols
                            // you've encountered an invalid character in the mini pascal language.
                            // go to ERROR state.
                            default:
                                stateNumber = ERROR;
                                currentLexeme.append(Character.toString((char) currentCharacter));
                                try {
                                    // other pushback
                                    input.unread(currentCharacter);
                                }
                                catch(IOException ioe){
                                    System.out.println("Illegal token " + currentLexeme);
                                    System.exit(-1);
                                }
                                break;
                        } // end of switch
                    } // end of else
                    break;
                // IN_ID_OR_KEYWORD state
                case IN_ID_OR_KEYWORD:
                    // you've reached the end of the file.
                    if(currentCharacter == -1) {
                        stateNumber = ID_OR_KEYWORD_COMPLETE;
                    }
                    // if you encounter a letter or digit (legal)
                    else if(Character.isLetterOrDigit(currentCharacter)) {
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter anything else
                    else {
                        // try to pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe){
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to ID_OR_KEYWORD_COMPLETE Ss
                        stateNumber = ID_OR_KEYWORD_COMPLETE;
                    }
                    break;
                // COMMENT state
                case COMMENT:
                    // if you encounter another '{'
                    if(currentCharacter == '{') {
                        // go to ERROR state
                        stateNumber = ERROR;
                        // Can't have two '{' in a row. 
                        // Append to indicate what is causing the error.
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter a '}'
                    else if(currentCharacter == '}') {
                        // your comment is complete
                        stateNumber = START;
                    }
                    // Stay in comment state if you find anything else.
                    break;
                // GREATER_THAN_COLON state
                case GREATER_THAN_COLON:
                    // if you encounter '=' after a '<' or ':'
                    if(currentCharacter == '=') {
                        // go to SHORT_SYMBOL_COMPLETE state
                        stateNumber = SHORT_SYMBOL_COMPLETE;
                        // append character to current lexeme.
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter anything else.
                    else {
                        // other pushback.
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to the SHORT_SYMBOL_COMPLETE state.
                        stateNumber = SHORT_SYMBOL_COMPLETE;
                    }
                    break;
                // if you encounter a '<'
                case LESS_THAN:
                    // if you then encounter a '>' or a '='
                    if(currentCharacter == '>' || currentCharacter == '=') {
                        // go to SHORT_SYMBOL_COMPLETE state.
                        stateNumber = SHORT_SYMBOL_COMPLETE;
                        // append char to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you see anything else
                    else {
                        // other pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to SHORT_SYMBOL_COMPLETE state.
                        stateNumber = SHORT_SYMBOL_COMPLETE;
                    }
                    break;
                // DIGIT state
                case DIGIT:
                    // if you've reached the end of the file
                    if(currentCharacter == -1) {
                        // go to NUMBER_COMPLETE state
                        stateNumber = NUMBER_COMPLETE;
                    }
                    // if you encounter a digit
                    else if(Character.isDigit(currentCharacter)) {
                        // append digit to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter a period
                    else if(currentCharacter == '.') {
                        // go to PERIOD state
                        stateNumber = PERIOD;
                        // append period to current lexeme.
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter a letter
                    else if(Character.isLetter(currentCharacter)) {
                        // If the character is a letter throw an error
                        // UNLESS YOU FOUND A CAPITAL E
                        if(currentCharacter == 'E'){
                            // append E to current lexeme
                            currentLexeme.append(Character.toString((char) currentCharacter));
                            // go to EXPONENET state
                            stateNumber = EXPONENT;
                        }
                        // oops you found something that wasn't a capital E
                        else {
                            // append to current lexeme
                            currentLexeme.append(Character.toString((char) currentCharacter));
                            // go to ERROR state
                            stateNumber = ERROR;
                        } // end else
                    } // end else if
                    // if you encounter anything else
                    else {
                        // other pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to NUMBER_COMPLETE state
                        stateNumber = NUMBER_COMPLETE;
                    }
                    break;
                // PERIOD state
                case PERIOD:
                    // if you encounter a digit
                    if(Character.isDigit(currentCharacter)) {
                        // go to OPTIONAL_FRACTION state
                        stateNumber = OPTIONAL_FRACTION;
                    }
                    // if you see anything else
                    else {
                        // other pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to ERROR state
                        stateNumber = ERROR;
                    }
                // OPTIONAL_FRACTION state
                case OPTIONAL_FRACTION:
                    // if you encouter a digit
                    if(Character.isDigit(currentCharacter)) {
                        // stay in the same state
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter a capital E
                    else if(currentCharacter == 'E') {
                        // go to the EXPONENT state
                        stateNumber = EXPONENT;
                        // append to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you see anything else
                    else {
                        // other pusbhack
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to NUMBER_COMPLETE state
                        stateNumber = NUMBER_COMPLETE;
                    }
                    break;
                // EXPONENT state
                case EXPONENT:
                    // if you encounter a digit
                    if(Character.isDigit(currentCharacter)) {
                        // go to OPTIONAL_EXPONENT state
                        stateNumber = OPTIONAL_EXPONENT;
                        // append digit to lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter a '+' or '-' symbol
                    else if(currentCharacter == '+' || currentCharacter == '-') {
                        //  go to SIGN state
                        stateNumber = SIGN;
                        // append symbol to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter anything else
                    else {
                        // other pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to ERROR state
                        stateNumber = ERROR;
                    }
                    break;
                // SIGN state
                case SIGN:
                    // if you encounter a digit
                    if(Character.isDigit(currentCharacter)) {
                        // go to OPTIONAL_EXPONENT state
                        stateNumber = OPTIONAL_EXPONENT;
                        // append digit to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you see anything else
                    else {
                        // other pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to ERROR state
                        stateNumber = ERROR;
                    }
                    break;
                // OPTIONAL_EXPONENT state
                case OPTIONAL_EXPONENT:
                    // if you encounter a digit
                    if(Character.isDigit(currentCharacter)) {
                        // Stay in the same state
                        // append digit to current lexeme
                        currentLexeme.append(Character.toString((char) currentCharacter));
                    }
                    // if you encounter anything else
                    else {
                        // other pushback
                        try {
                            input.unread(currentCharacter);
                        }
                        catch(IOException ioe) {
                            System.out.println("Illegal token " + currentLexeme);
                            System.exit(-1);
                        }
                        // go to NUMBER_COMPLETE state
                        stateNumber = NUMBER_COMPLETE;
                    }
                    break;
            } // end switch
        } // end while
        
        // set lexeme to the current lexeme that was built.
        this.lexeme = currentLexeme.toString();
        
        // if you're in the ERROR state
        if(stateNumber == ERROR) {
            // print error message, set token type to null and then return false.
            System.out.println("Error! Invalid token " + this.lexeme + " was found.");
            this.type = null;
            return false;
        }
        // if you're in the ID_OR_KEYWORD_COMPLETE state
        else if(stateNumber == ID_OR_KEYWORD_COMPLETE) {
            // if the type of the lexeme is null,
            // then it's not a KEYWORD. It's an ID.
            this.type = lookup.get(this.lexeme);
            if(this.type == null) {
                this.type = TokenType.ID;
            }
            return true;
        }
        // if you're in the SHORT_SYMBOL_COMPLETE state
        else if(stateNumber == SHORT_SYMBOL_COMPLETE) {
            // look up the short symbol in the LookupTable
            this.type = lookup.get(this.lexeme);
            return true;
        }
        // if you're in the SYMBOL_COMPLETE state
        else if(stateNumber == SYMBOL_COMPLETE) {
            // look up the symbol in the LookupTable
            this.type = lookup.get(this.lexeme);
            return true;
        }
        // if you're in the NUMBER_COMPLETE state
        else if(stateNumber == NUMBER_COMPLETE) {
            // as long as you have either a period or a capital E in your lexeme
            this.type = TokenType.NUM;
            return true;
        }
        
        return false;
    } // end of function.
    
     /** 
     * Returns the token type.
     * @return The token type of the most recent token.
     */
    public TokenType getToken() {
        return this.type;
    }
    
    /**
     * Returns the lexeme.
     * @return The lexeme of the most recent token.
     */
    public String getLexeme() {
        return this.lexeme;
    }
} // end of Scanner class
