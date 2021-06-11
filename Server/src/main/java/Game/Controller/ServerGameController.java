package Game.Controller;

import Game.Exceptions.NotAvailableUserException;
import User.Controller.UserController;
import User.Model.User;

import java.sql.SQLException;

public class ServerGameController {
    User user1;
    User user2;


    public ServerGameController(String[] userUUIDs) throws SQLException, NotAvailableUserException {
        User user1 = new User();
        UserController user1Controller = new UserController(user1);
        user1Controller.readUserDataByUUID(userUUIDs[0]);
        this.user1 = user1;

        User user2 = new User();
        UserController user2Controller = new UserController(user2);
        user2Controller.readUserDataByUUID(userUUIDs[1]);
        this.user2 = user2;

        initialize();


    }

    public void initialize() throws NotAvailableUserException {
        boolean isBothAreAvailable = checkUsersAvailability();
        if(!isBothAreAvailable){
            throw new NotAvailableUserException("User not available");
        }
    }

    public boolean checkUsersAvailability(){
        boolean isUser1Available = informNewGameToUser(user1.getUuid());
        boolean isUser2Available = informNewGameToUser(user2.getUuid());
        if(isUser1Available && isUser2Available){
            return true;
        }
        else {
            return false;
        }
    }
}
