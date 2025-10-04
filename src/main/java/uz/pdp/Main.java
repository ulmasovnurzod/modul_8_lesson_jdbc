package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.pdp.config.SpringConfig;
import uz.pdp.daos.UserDAO;
import uz.pdp.entities.Users;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scInt = new Scanner(System.in);
    static Scanner scSrt = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);



    public static void main(String[] args) {


         userDAO = context.getBean(UserDAO.class);

        System.out.println("salom xush kelibsiz");
        int step = -1;
        while (step !=0 ) {
            System.out.print("""
                    1. Creade User
                    2. Update User
                    3. Get Users
                    4. Get User
                    5.Delete User
                    >>>\s""");
            step = scInt.nextInt();
            switch (step){
                case 1 -> {
                    Users user = new Users();
                    System.out.print("fullName kiriting : ");
                     user.setFullName(scSrt.nextLine());
                    System.out.print("email kiritting : ");
                    user.setEmail(scSrt.nextLine());
                    userDAO.addUsers(user);
                }
                case 2 -> {
                    Users users = new Users();
                    System.out.print("qaysini o'zgartirmoqchsiz : ");
                    users.setId( scInt.nextInt());
                    System.out.print("yangi fullName kiriting : ");
                    users.setFullName(scSrt.nextLine());
                    System.out.print("yangi emailni kiriting : ");
                    users.setEmail(scSrt.nextLine());
                    userDAO.updateUser(users);
                }
                case 3 -> {
                    List<Users> allUsers = userDAO.getAllUsers();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(allUsers);
                    System.out.println(json);

                }
                case 4 -> {
                    System.out.print("kerakli user idsini kiriting : ");
                    Integer id = scInt.nextInt();
                        Users userById = userDAO.getUserById(id);
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(userById);
                    System.out.println(json);

                }
                case 5 -> {
                    System.out.print("qaysi idili userni delet qilmoqchisiz : ");
                    Integer id = scInt.nextInt();
                    userDAO.delete(id);
                }
            }
        }


    }
}