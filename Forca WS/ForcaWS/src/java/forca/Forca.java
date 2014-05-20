package forca;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "Forca")
public class Forca {
    private Player player = Player.getInstance();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "name") String name, @WebParam(name = "id") String id, @WebParam(name = "word") String word) {
        DataUser du = new DataUser(name, id, word.toUpperCase());
        return player.registerUser(du) + ":::";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "play")
    public String play(@WebParam(name = "name") String name, @WebParam(name = "id") String id, @WebParam(name = "input") String input) {
        int operation;
        if (id.equals("") && input.equals("")) {
            operation = player.startForca(name);
        } else if (!name.equals("") && !id.equals("") && !input.equals("")) {
            operation = player.throwWord(id, name, input.toUpperCase());
        } else {
            operation = 3;
        }
        
        if (operation == 3) {
            return "3:::";
        } else {
            DataUser dUser = player.findUser(name);
            DataThrow dThrow = dUser.getdThrow();
            return operation + ":" + dUser.getId() + ":" + dThrow.getErrors() + ":" + dThrow.getWordMask();
        }
    }
}