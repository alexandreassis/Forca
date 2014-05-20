package forca;

public class DataThrow {
    private String wordMask;
    private int errors;
    private String letterUsed;
    private boolean endgame;

    public DataThrow(String wordMask) {
        this.wordMask = wordMask;
        this.errors = 0;
        this.letterUsed = "";
        this.endgame = false;
    }

    public String getLetterUsed() {
        return this.letterUsed;
    }

    public void setLetterUser(String letters) {
        this.letterUsed = letters;
    }

    public String getWordMask() {
        return this.wordMask;
    }

    public int getErrors() {
        return this.errors;
    }

    public void setWordMask(String wordMask) {
        this.wordMask = wordMask;
    }

    public void setErrors(int error) {
        this.errors = error;
    }

    public boolean isEndgame() {
        return endgame;
    }

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }
}