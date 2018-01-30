package com.parlp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    private static Locations locations = new Locations();
    private static final int STARTING_POINT = 1;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Map<String, String> commandsVocabulary = new HashMap<>();
        commandsVocabulary.put("QUIT", "Q");
        commandsVocabulary.put("NORTH", "N");
        commandsVocabulary.put("SOUTH", "S");
        commandsVocabulary.put("WEST", "W");
        commandsVocabulary.put("EAST", "R");

        Location currentLocation = locations.getLocation(STARTING_POINT);

        while (true) {
            System.out.println(currentLocation.getDescription());

            if (currentLocation.getLocationId() == 0)
                break;

            Map<String, Integer> exits = currentLocation.getExits();

            System.out.print("Available exits: ");
            for (String exit : exits.keySet()) {
                System.out.print(String.format("%s, ", exit));
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] userInputWords = direction.split(" ");
                for (String word : userInputWords)
                    if (commandsVocabulary.containsKey(word.toUpperCase())) {
                        direction = commandsVocabulary.get(word);
                        break;
                    }
            }

            if (exits.containsKey(direction))
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));
            else
                System.out.println("Cannot go to that direction");
        }
        locations.close();
    }
}
