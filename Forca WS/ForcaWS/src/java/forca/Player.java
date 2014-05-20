package forca;

import java.util.*;

public class Player {

    private static Player player = null;
    private ArrayList<DataUser> users;

    private Player() {
        users = new ArrayList<DataUser>();
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public DataUser findUser(String userName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equalsIgnoreCase(userName)) {
                return users.get(i);
            }
        }
        return null;
    }
    
    private boolean exists(String id, String name) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(id) || users.get(i).getUserName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private DataUser findUserByIdAndName(String id, String name) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getUserName().equalsIgnoreCase(name)) {
                return users.get(i);
            }
        }
        return null;
    }

    public int registerUser(DataUser user) {
        if (findUser(user.getUserName()) != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(user.getUserName())) {
                    users.remove(i);
                }
            }
        }
        
        if(exists(user.getId(), user.getUserName())) {
            return Global.REP_ERROR;
        }

        users.add(user);
        System.out.println("Usuario " + user.getUserName() + " registrado com sucesso!");
        return Global.REP_OK;
    }

    public int startForca(String username) {
        DataUser du = findUser(username);
        if (du == null || du.isStarted()) {
            return Global.REP_ERROR;
        }

        String aux = "";
        for (int i = 0; i < du.getWord().length(); i++) {
            aux = aux + "_";
        }

        DataThrow dt = new DataThrow(aux);
        du.setdThrow(dt);
        du.setStarted(true);
        updateWord(du);
        System.out.println(du.getId() + " iniciou a partida.");
        return Global.REP_OK;
    }

    public int throwWord(String id, String name, String letter) {
        DataUser du = findUserByIdAndName(id, name);
        if (du == null || !du.isStarted()) {
            return Global.REP_ERROR;
        }

        DataThrow dt = du.getdThrow();
        if (dt.isEndgame()) {
            return Global.REP_ERROR;
        }

        String word = du.getWord();

        if (!word.contains(letter) && !dt.getLetterUsed().contains(letter)) {
            System.out.println(id + " errou a letra.");
            dt.setErrors(dt.getErrors() + 1);
        } else {
            System.out.println(id + " acertou a letra.");
            String newWordMaked = "";
            for (int i = 0; i < word.length(); i++) {
                if (String.valueOf(word.charAt(i)).equals(letter)) {
                    newWordMaked += letter;
                } else {
                    newWordMaked += dt.getWordMask().substring(i, i + 1);
                }
            }
            dt.setWordMask(newWordMaked);
        }

        dt.setLetterUser(dt.getLetterUsed() + letter);
        du.setdThrow(dt);

        // retornar a operação
        if (du.getWord().equalsIgnoreCase(dt.getWordMask()) && dt.getErrors() < 6) {
            dt.setEndgame(true);
            System.out.println(du.getId() + " acertou a palavra.");
            return 1;
        } else if (!du.getWord().equalsIgnoreCase(dt.getWordMask()) && dt.getErrors() == 6) {
            dt.setEndgame(true);
            System.out.println(du.getId() + " errou a palavra.");
            return 2;
        }

        return Global.REP_OK;
    }

    private void updateWord(DataUser user) {
        final String character = "QWERTYUIOPASDFGHJKLÇZXCVBNM";

        String word = user.getWord();

        for (int i = 0; i < word.length(); i++) {
            if (!character.contains(String.valueOf(word.charAt(i)))) {
                DataThrow dThrow = user.getdThrow();
                dThrow.setWordMask(replaceCharAt(dThrow.getWordMask(), i, String.valueOf(word.charAt(i))));
            }
        }
    }

    private String replaceCharAt(String s, int pos, String c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }
}