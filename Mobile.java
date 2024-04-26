import java.util.Arrays;

class Mobile extends Gadget
{
    private int minutes;

    public Mobile(String model, double price, int weight, String size,int callMinutes)
    {
        super(model, price, weight, size);
        minutes = callMinutes;
    }

    public int getMinutes()
    {   
        return minutes;
    }

    /**
     * Adds the number of minutes given by the user to the number of available minutes (credit).
     * callCredit = amount of time available for calling another person, in minutes.
     */
    public void addMinutes(int callCredit)
    {
        if(callCredit > 0) {
            minutes += callCredit;
        }
        else {
            System.out.println("Additional credit must be a positive whole number");
        }
    }

    /**
     * Checks wether the number given by the user is a valid phone number.
     * The phone number must start with 07 and be 11 digits long.
     */
    private boolean isValidPhoneNumber(String phoneNumber)
    {
        boolean isValid = false;
        if(phoneNumber.startsWith("07")) {
            if(phoneNumber.length() == 11) {
                boolean isPhoneNumber = true;
                //Iterate over every character of the given string.
                for(char stringCharacter : phoneNumber.toCharArray()) {
                    //Check if the character is not a digit.
                    if(!Character.isDigit(stringCharacter)) {
                        isPhoneNumber = false;
                        //Break the loop if one of the characters of the given string is not a digit.
                        break;
                    }
                }
                //Confirm that all the characters of the given string are digits.
                if(isPhoneNumber) {
                    isValid = true;
                }
                else {
                    System.out.println("One or more digits have ben input incorrectly (phone numbers are 11 digits long and start with '07')");
                }
            }
            else {
                System.out.println("Incorrect amount of digits (phone numbers are 11 digits long and start with '07')");
            }
        }
        else {
            System.out.println("Not a valid phone number (phone numbers are 11 digits long and start with '07')");
        }
        return isValid;
    }

    /**
     * Simulates a call by prompting the user to input the number to call and call duration.
     * The call duration must be a positive whole number that does not exceed the amount of minutes available, and the number must be a valid phone number as requested by isValidPhoneNumber().
     * Display suitable error messages if those conditions are not met.
     */
    public void callNumber(String number, int callDurationInMinutes)
    {
        if(callDurationInMinutes <= 0) {
            System.out.println("Please insert valid amount for the duration");
        }
        else if(number == null || number.isEmpty()) {
            System.out.println("Please provide a valid phone number");
        }
        else if(callDurationInMinutes > minutes) {
            System.out.println("You do not have enough minutes available. Please top up and try again.");
        }
        else if(!isValidPhoneNumber(number)) {
           //isValidPhoneNumber will display an error message specific to the error
        }
        else {
            System.out.println("Calling " + number + " for " + callDurationInMinutes + " minutes");
            //Update the amount of credit held by the mobile.
            minutes -= callDurationInMinutes;
        }
    }

    /**
     * Display the mobile's information on the terminal in a suitably annotated manner.
     */
    public void print()
    {   
        super.print();
        System.out.println("Credit: "+ minutes + " minutes\n ");
    }
}