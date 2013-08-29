package client;

import com.sun.jersey.api.client.UniformInterfaceException;
import entity.Player;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Michal Szpytko
 * sluzy jako test klasy lokalnej klasy PlayerServiceClient wykorzystujacej 
 * serwis REST PlayerService    
 */
public class TestPlayerServiceClient {

    public static void main(String[] args) {

        entity.Player player = new Player();
        PlayerServiceClient client = new PlayerServiceClient();

        System.out.println("Test @GET count");
        Object response = client.countREST();
        System.out.println("WYNIK: count=" + response.toString());

        System.out.println("Test @GET (jako findAll_XML)");
        String result = client.findAll_XML(String.class);
        System.out.println("WYNIK: result=" + result);
        
        try {
            System.out.println("Test @GET {id}");
            result = client.find_XML(Player.class, "1");
            System.out.println("WYNIK find_XML: result=" + result);
        } catch (UniformInterfaceException ex0) {
            System.out.println("Exception ex0=" + ex0.getMessage());
        }
        
        try {
            System.out.println("Test @GET {id}");
            player = client.find_JSON(Player.class, "2");
            System.out.println("WYNIK find_JSON: player=" + player);

        } catch (UniformInterfaceException ex1) {
            System.out.println("Exception ex1=" + ex1.getMessage());
        }

        try {
            System.out.println("Test @DELETE {id}");
            client.remove("2");
            System.out.println("WYNIK findAll_XML po remove=" + client.findAll_XML(String.class));

        } catch (UniformInterfaceException ex2) {
            System.out.println("Exception ex2=" + ex2.getMessage());
        }

        try {
            System.out.println("Test @POST");
            player.setId(6);
            player.setLastname("Chrobry");
            player.setFirstname("Boleslaw");
            player.setTeamname("Arka");
            client.create_JSON(player);
            System.out.println("WYNIK findAll_XML po create=" + client.findAll_XML(String.class));
        } catch (UniformInterfaceException ex3) {
            System.out.println("Exception ex3=" + ex3.getMessage());
        }

        try {
            System.out.println("Test @POST");
            player.setId(7);
            player.setLastname("Grozny");
            player.setFirstname("Iwan");
            player.setTeamname("Arka");
            client.create_JSON(player);
            System.out.println("WYNIK findAll_XML po create=" + client.findAll_XML(String.class));
        } catch (UniformInterfaceException ex4) {
            System.out.println("Exception ex4=" + ex4.getMessage());
        }
        System.out.println("Test @PUT");
        player = client.find_JSON(entity.Player.class, "1");

        System.out.println("pobieram obiekt o id=1 WYNIK=" + player);
        player.setTeamname("Wisla Krakow");
        client.edit_JSON(player);
        System.out.println("WYNIK findAll_XML po edit=" + client.findAll_XML(String.class));

        System.out.println("Test @GET player/{lastname}");
        player = client.findByLastname_JSON(Player.class, "Chrobry");
        System.out.println("WYNIK find_JSON: player=" + player);

        System.out.println("Test @GET findAll_JSON");
        List<Player> players = client.findAll_JSON(Player.class);
        System.out.println("WYNIK findAll_JSON: players=" + players);
        Iterator<Player> iterator = players.iterator();

        while (iterator.hasNext()) {
            player = iterator.next();
            System.out.println("[" + player.getId() + "," + player.getFirstname() + "," + player.getLastname() + "," + player.getTeamname() + "]");
        }

        client.close();
    }
}
