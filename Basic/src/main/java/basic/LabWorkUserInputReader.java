package basic; /**
 * a class used for getting user input for creating a copy of main.LabWork
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class LabWorkUserInputReader {

    BufferedReader br;

    public LabWorkUserInputReader(BufferedReader br) {
        this.br = br;
    }

    /**
     * the method used to get user input for creating a copy of main.LabWork
     *
     * @return a main.LabWork with the values entered by a user
     */
    public LabWork getUserInput() throws IOException {

        String name = "";
        while (true) {
            System.out.println("enter the name:");
            name = br.readLine();
            if (name.isEmpty()) {
                System.out.println("the name can't be blank");
                continue;
            }
            break;
        }

        long x;
        while (true) {
            System.out.println("enter the x coordinate:");
            try {
                x = Long.parseLong(br.readLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("it looks like what you entered is not an integer");
                continue;
            }
            break;
        }

        int y;
        while (true) {
            System.out.println("enter the y coordinate:");
            try {
                y = Integer.parseInt(br.readLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("it looks like what you entered is not an integer");
                continue;
            }
            break;
        }

        Coordinates coordinates = new Coordinates(x, y);

        double minimalPoint;
        while (true) {
            System.out.println("enter the minimal point:");
            try {
                minimalPoint = Double.parseDouble(br.readLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("it looks like what you entered is not a number");
                continue;
            }
            if (minimalPoint < 0) {
                System.out.println("the minimal point has to be positive");
                continue;
            }
            break;
        }

        String description = "";
        while (true) {
            System.out.println("enter the description:");
            description = br.readLine();
            if (description != null) {
                if (description.length() > 843) {
                    System.out.println("the description can not be longer than 843 symbols");
                    continue;
                }
            }
            break;
        }

        Difficulty difficulty;
        while (true) {
            System.out.println("enter the difficulty (VERY_EASY, HARD, VERY_HARD, IMPOSSIBLE, HOPELESS):");
            try {
                difficulty = Difficulty.valueOf(br.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println("you can only choose one of the given options");
                continue;
            }
            break;
        }

        String authorName = "";
        while (true) {
            System.out.println("enter the name of the author:");
            authorName = br.readLine();
            if (authorName == null || authorName.isEmpty()) {
                System.out.println("the name of the author can't be blank");
                continue;
            }
            break;
        }

        int authorHeight;
        while (true) {
            System.out.println("enter the height of the author:");
            try {
                authorHeight = Integer.parseInt(br.readLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("it looks like what you entered is not an integer");
                continue;
            }
            if (authorHeight < 0) {
                System.out.println("the height of the author has to be positive");
                continue;
            }
            break;
        }

        Long authorWeight;
        while (true) {
            System.out.println("enter the weight of the author:");
            try {
                authorWeight = Long.valueOf(br.readLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("it looks like what you entered is not an integer");
                continue;
            }
            if (authorWeight < 0) {
                System.out.println("the weight of the author has to be positive");
                continue;
            }
            break;
        }

        String authorPassportId = "";
        while (true) {
            System.out.println("enter the passport id of the author:");
            authorPassportId = br.readLine();
            if (authorPassportId == null) {
                System.out.println("somehow the passport id is null, try again");
                continue;
            }
            break;
        }

        Color eyeColor;
        while (true) {
            System.out.println("enter the eye color of the author (GREEN, RED, BLACK, ORANGE, BROWN):");
            try {
                eyeColor = Color.valueOf(br.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println("you can only choose one of the given options");
                continue;
            }
            break;
        }

        Person author = new Person(authorName, authorHeight, authorWeight, authorPassportId, eyeColor);

        return (new LabWork(name, coordinates, minimalPoint, description, difficulty, author));

    }

}
