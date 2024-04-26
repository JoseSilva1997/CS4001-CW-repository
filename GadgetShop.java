import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GadgetShop implements ActionListener
{
    private JFrame frame;
    private JTextField textFieldModel;
    private JTextField textFieldPrice;
    private JTextField textFieldWeight;
    private JTextField textFieldSize;
    private JTextField textFieldCredit;
    private JTextField textFieldMemory;
    private JTextField textFieldDisplayNr;
    private JTextField textFieldFileSize;
    private JTextField textFieldPhoneNr;
    private JTextField textFieldDuration;
    private JButton addMobileButton;
    private JButton addMP3Button;
    private JButton downloadMusicButton;
    private JButton makeCallButton;
    private JButton clearButton;
    private JButton displayAllButton;
    private ArrayList<Gadget> gadgetsArrayList;
    //Added an extra button that, when pressed, deletes music from a selected MP3.
    private JButton deleteMusicButton;
    //Added an extra button that, when pressed, sells the selected gadget.
    private JButton sellGadgetButton;
    //These two constants will be used to set various components to the same size, improving consistency.
    private static final int COMPONENT_WIDTH = 200;
    private static final int COMPONENT_HEIGHT = 25;

    public GadgetShop()
    {
        makeFrame();
        gadgetsArrayList = new ArrayList<Gadget>();
    }

      public static void main(String[] args)
    {
        GadgetShop shop = new GadgetShop();
    }
    
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();
        if(command.equals("Add Mobile")) {
            addMobile();
        }

        if(command.equals("Add MP3")) {
            addMP3();
        }

        if(command.equals("Download music")) {
            downloadMusic();
        }

        if(command.equals("Make call")) {
            makeCall();
        }

        if(command.equals("Display all")) {
            list();
        }

        if(command.equals("Clear")) {
            clear();
        }

        if(command.equals("Delete music")) {
            deleteMusic();
        }

        if(command.equals("Sell gadget")) {
            sellGadget();
        }
    }

    /**
     * Adds a new MP3 to gadgetsArrayList if all attributes have been input correctly 
     * and displays suitable error messages if certain conditions are not met.
     */
    public void addMP3()
    {   
        //Check if the user has input a model or a size.
        if(getTextFieldModel() == null || getTextFieldSize() == null) {
            JOptionPane.showMessageDialog(frame,"Please fill in all the required fields correctly." , "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Check if space, price, and weight are valid inputs
        else if(getTextFieldPrice() != -1 && getTextFieldWeight() != -1 && getTextFieldMemory() != -1) {
            //Create an object of type MP3 with the specified attributes.
            Gadget newMp3 = new MP3(getTextFieldModel(),getTextFieldPrice(),getTextFieldWeight(),getTextFieldSize(),getTextFieldMemory());
            gadgetsArrayList.add(newMp3);
            //Clear all text boxes
            clear();
            JOptionPane.showMessageDialog(frame,"MP3 added successfully!");
        }
        else {
            //A suitable error message will be displayed by the get method where the error occurred.
            return;
        }
    }

    /**
     * Adds a new Mobile to gadgetsArrayList if all attributes have been input correctly 
     * and displays suitable error messages if certain conditions are not met.
     */
    public void addMobile()
    {
        //Check if the user has input a model or a size.
        if(getTextFieldModel() == null || getTextFieldSize() == null) {
            JOptionPane.showMessageDialog(frame,"Please fill in all the required fields correctly." , "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Check if credit, price, and weight are valid inputs
        else if(getTextFieldPrice() != -1 && getTextFieldWeight() != -1 && getTextFieldCredit() != -1) {
            Gadget newMobile = new Mobile(getTextFieldModel(),getTextFieldPrice(),getTextFieldWeight(),getTextFieldSize(),getTextFieldCredit());
            gadgetsArrayList.add(newMobile);
            //Clear all text boxes.
            clear();
            JOptionPane.showMessageDialog(frame,"Mobile added successfully!");
        }
        else {
            //A suitable error message will be displayed by the get method where the error occurred.
            return;
        }
    }   

    /** 
     * Downloads music onto a selected MP3, and displays
     * a suitable error messages if certain conditions are not met.
     */
    public void downloadMusic()
    {   
        if(gadgetsArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "There are currently no gadgets", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            //Confirm that the display number given is valid.
            if(getTextFieldDisplayNumber() != -1) {
                //if(gadgetsArrayList.get(getTextFieldDisplayNumber()) instanceof MP3) {
                    MP3 sellectedGadget = (MP3) gadgetsArrayList.get(getTextFieldDisplayNumber());
                    sellectedGadget.downloadSong(getTextFieldFileSize());
                //}
                //else {
                    JOptionPane.showMessageDialog(frame, "Please select an MP3 from the list", "Error", JOptionPane.ERROR_MESSAGE);
                //}
            }
            else {
                //A suitable error message will be displayed by the getTextFieldDisplayNumber() method.
                return;
            }
        }
    }

    /**
     * Deletes songs from the MP3 to provide more space for other songs.
     * Displays suitable error messages if certain conditions are not met.
     */
    public void deleteMusic()
    {
        if(gadgetsArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "There are currently no gadgets", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            //Confirm that the display number given is valid.
            if(getTextFieldDisplayNumber() != -1) {
                if(gadgetsArrayList.get(getTextFieldDisplayNumber()) instanceof MP3) {
                    MP3 sellectedGadget = (MP3) gadgetsArrayList.get(getTextFieldDisplayNumber());
                    sellectedGadget.deleteSong(getTextFieldFileSize());
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Please select an MP3 from the list", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                //A suitable error message will be displayed by the getTextFieldDisplayNumber() method.
                return;
            }
        }
    }

    /** 
     * Initiates a call from a selected Mobile gadget to a specified phone number for a given duration
     * and displays suitable error messages if certain conditions are not met.
     */
    public void makeCall()
    {
        if(gadgetsArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "There are currently no gadgets", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            //Confirm that the display number given is valid.
            if(getTextFieldDisplayNumber() != -1) {
                if(gadgetsArrayList.get(getTextFieldDisplayNumber()) instanceof Mobile) {
                    Mobile sellectedMobile = (Mobile) gadgetsArrayList.get(getTextFieldDisplayNumber());
                    sellectedMobile.callNumber(getTextFieldPhoneNr(), getTextFieldDuration());
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Please select a Mobile from the list", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                //A suitable error message will be displayed by the getTextFieldDisplayNumber() method.
                return;
            }
        }
    }

    /**
     * Sells a gadget from Gadgets array list removing it from the list once the transaction is completed.
     * Display an input window prompting the user to pay an amount equal to the price of the selected gadget. Repeat this until the amount given by the user is less than the amount required.
     * 
     */
    private void sellGadget()
    {
        if(gadgetsArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Currently out of stock. Please add some gadgets to stock.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            //Confirm that the display number given is valid.
            if(getTextFieldDisplayNumber() != -1) {
                double gadgetPrice = gadgetsArrayList.get(getTextFieldDisplayNumber()).getPrice();
                double currentBalance = 0;
                double totalToPay = gadgetPrice;
                //Prompt the user for more money until enough has been given to buy the gadget.
                while(currentBalance < gadgetPrice) {
                    //Display an input message box prompting the user to insert the amount of money needed to pay for the selected gadget.
                    String userInput = JOptionPane.showInputDialog(frame, "This item costs: £" + gadgetPrice + "\nYou paid: £" + currentBalance + "\nAmount to pay: £" + totalToPay);
                    //Check if user has pressed "cancel" or closed the window.
                    if(userInput != null) {
                        try{
                            double moneyReceived = Double.parseDouble(userInput);
                            if(moneyReceived <= 0) {
                                JOptionPane.showMessageDialog(frame, "Please insert a positive amount of money.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                currentBalance += moneyReceived;
                                totalToPay = gadgetPrice-currentBalance;
                            }
                        } catch(NumberFormatException e) {
                            JOptionPane.showMessageDialog(frame, "Please input a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        //Stop the method if the user has pressed "cancel or closes the window and display an appropriate message.
                        JOptionPane.showMessageDialog(frame, "Transaction canceled", "Canceled", JOptionPane.WARNING_MESSAGE);
                        clear();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Thank you for your purchase!\nHere's your change: £" + (currentBalance-gadgetPrice), "Transaction completed", JOptionPane.INFORMATION_MESSAGE);
                gadgetsArrayList.remove(getTextFieldDisplayNumber());
                clear();
            }
            else {
                //A suitable error message will be displayed by the getTextFieldDisplayNumber() method.
                return;
            }
        }
    }

    /**
     * Prints a list of all available gadgets stored in GadgetsArrayList along with their respective details.
     */
    public void list()
    {   
        if (gadgetsArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Currently out of stock. Please add some gadgets to stock.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            //Iterate over every gadget in the array list and display its information
            for(Gadget gadget : gadgetsArrayList) {
                //Display a header with the display number and class of the gadget
                System.out.println("Display number: " + (gadgetsArrayList.indexOf(gadget) + " ("+ gadget.getClass() + ")"));
                gadget.print();
            }
        }
    }

    /**
     * Clears all text fields.
     */
    public void clear()
    {
        textFieldModel.setText(null);
        textFieldPrice.setText(null);
        textFieldWeight.setText(null);
        textFieldSize.setText(null);
        textFieldCredit.setText(null);
        textFieldMemory.setText(null);
        textFieldDisplayNr.setText(null);
        textFieldDuration.setText(null);
        textFieldPhoneNr.setText(null);
        textFieldFileSize.setText(null);
    }

    /**
     * Retrieves user input from the model text field.
     * Returns its value if the field is not empty, otherwise return null to indicate an error.
     */
    public String getTextFieldModel()
    {
        String model = textFieldModel.getText();
        if(model.isEmpty()) {
            return null;
        }
        else {
            return model;
        }
    }

    /**
     * Retrieves user input from the price text field.
     * Returns its value if the field is not empty and if it's a positive number. 
     * Otherwise, return -1 to indicate an error.
     */
    public double getTextFieldPrice()
    {
        double parsedPrice = -1;
        try {
            parsedPrice = Double.parseDouble(textFieldPrice.getText());
            if(parsedPrice <= 0.0) {
                JOptionPane.showMessageDialog(frame,"Please insert a positive number as the price." , "Error", JOptionPane.ERROR_MESSAGE);
                parsedPrice = -1;
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Please insert a number as the price." , "Error", JOptionPane.ERROR_MESSAGE);
        }
        return parsedPrice;
    }

    /**
     * Retrieves user input from the weight text field.
     * Returns its value if the field is not empty and if it's a positive number. 
     * Otherwise, return -1 to indicate an error.
     */
    public int getTextFieldWeight()
    {
        int parsedWeight = -1;
        try {
            parsedWeight = Integer.parseInt(textFieldWeight.getText());
            if(parsedWeight <= 0) {
                JOptionPane.showMessageDialog(frame,"Please insert a positive number for weight." , "Error", JOptionPane.ERROR_MESSAGE);
                parsedWeight = -1; 
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Please insert a valid integer for weight." , "Error", JOptionPane.ERROR_MESSAGE);
        }
        return parsedWeight;
    }

    /**
     * Retrieves user input from the size text field.
     * Returns its value if the field is not empty, otherwise return null to indicate an error.
     */
    public String getTextFieldSize()
    {
        String size = textFieldSize.getText();
        if(size.isEmpty()) {
            return null;
        }
        else{
            return size;
        }
    }

    /**
     * Retrieves user input from the credit text field.
     * Returns its value if the field is not empty and if it's a positive number. 
     * Otherwise, return -1 to indicate an error.
     */
    public int getTextFieldCredit()
    {
        int parsedCredit = -1;
        try {
            parsedCredit = Integer.parseInt(textFieldCredit.getText());
            if(parsedCredit <= 0) {
                JOptionPane.showMessageDialog(frame,"Please insert a positive amount of credit." , "Error", JOptionPane.ERROR_MESSAGE);
                parsedCredit = -1;
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Please insert a valid amount of credit." , "Error", JOptionPane.ERROR_MESSAGE);
        }
        return parsedCredit;
    }

    /**
     * Retrieves user input from the memory text field.
     * Returns its value if the field is not empty and if it's a positive number. 
     * Otherwise, return -1 to indicate an error.
     */
    public int getTextFieldMemory()
    {
        int parsedMemory = -1;
        try {
            parsedMemory = Integer.parseInt(textFieldMemory.getText());
            if(parsedMemory <= 0) {
                JOptionPane.showMessageDialog(frame,"Please insert a positive amount of memory." , "Error", JOptionPane.ERROR_MESSAGE);
                parsedMemory = -1;
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Please insert a valid amount of memory." , "Error", JOptionPane.ERROR_MESSAGE);
        }
        return parsedMemory;
    }

    /**
     * Retrieves user input from the fileSize text field.
     * Returns its value if the field is not empty and if it's a positive number. Otherwise, return -1 to indicate an error.
     * Errors caught by this method will be logged by this method as error messages and displayed in the terminal by 
     * the method in MP3 that requires the contents of the fileSize text field.
     */
    public int getTextFieldFileSize()
    {
        int fileSize = -1;
        try {
            fileSize = Integer.parseInt(textFieldFileSize.getText());
            if(fileSize <= 0) {
                System.err.println("Download size should be a positive number.");
                fileSize = -1;
            }
        } catch(NumberFormatException e) {
            System.err.println("Error parsing textFieldSize's contents. Unable to parse as an integer.");
        }
        return fileSize;
    }

    /**
     * Retrieves user input from the duration text field.
     * Returns its value if the field is not empty and if it's a positive number. Otherwise, return -1 to indicate an error.
     * Errors caught by this method will be logged by this method as error messages and displayed in the terminal by 
     * the method in Mobile that requires the contents of the Duration text field.
     */
    public int getTextFieldDuration()
    {
        int parsedDuration = -1;
        try {
            parsedDuration = Integer.parseInt(textFieldDuration.getText());
            if(parsedDuration <= 0) {
                System.err.println("Duration should be a positive number.");
            }
        } catch(NumberFormatException e) {
            System.err.println("Error parsing textFieldDuration's contents. Unable to parse as an integer.");
        }
        return parsedDuration;
    }

    /**
     * Retrieves user input from the phoneNr text field.
     * Returns its value if the field is not empty, otherwise return null to indicate an error.
     * Errors caught by this method will be logged by this method as error messages and displayed in the terminal by 
     * the method in Mobile that requires the contents of the PhoneNr text field.
     */
    public String getTextFieldPhoneNr()
    {
        String phoneNumber = textFieldPhoneNr.getText();
        if(phoneNumber.isEmpty()) {
            return null;
        }
        else {
            return phoneNumber;
        }
    }

    /**
     * Retrieves user input from the displayNumber text field.
     * Returns its value if it is a number corresponding to the index of one of the elements of GadgetsArrayList. 
     * Otherwise, return -1 to indicate an error.
     */
    public int getTextFieldDisplayNumber()
    {
        int displayNumber = -1;
        try {
            int inputDisplayNumber = Integer.parseInt(textFieldDisplayNr.getText());
            if((inputDisplayNumber < 0)) {
                JOptionPane.showMessageDialog(frame,"The display number must be a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //Check if inputDisplayNumber corresponds to the index of one of the elements of gadgetsArrayList.
            else if(inputDisplayNumber > gadgetsArrayList.size() - 1) {
                JOptionPane.showMessageDialog(frame,"There is no gadget with that display number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                displayNumber = inputDisplayNumber;
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Please insert a valid display number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return displayNumber;
    }

    /**
     * Creates a JPanel with a BoxLayout along the Y axis and sets it's preferred size to the x, y dimensions given.
     * Then, return the created panel.
     */ 
    private JPanel createPanel(int x, int y)
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(x,y));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    /**
     * Sets the size of the given JComponent to predetermined dimensions.
     */
    private void setComponentSize(JComponent component)
    {
        component.setMinimumSize(new Dimension(COMPONENT_WIDTH,COMPONENT_HEIGHT));
        component.setPreferredSize(new Dimension(COMPONENT_WIDTH,COMPONENT_HEIGHT));
        component.setMaximumSize(new Dimension(COMPONENT_WIDTH,COMPONENT_HEIGHT));
    }

    /**
     * Creates a new JTextField component with some predetermined  characteristics.
     * Then, return the created component.
     */
    private JTextField createTextField()
    {
        JTextField textField = new JTextField(); 
        setComponentSize(textField);
        textField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        return textField;
    }

    /**
     * Creates a new JButton component with predetermined dimensions. 
     * Then, return the created component.
     */
    private JButton createButton(String buttonName)
    {
        JButton newButton = new JButton(buttonName);
        newButton.addActionListener(this);
        setComponentSize(newButton);
        return newButton;
    }

    /**
     * Creates the GUI.
     */
    private void makeFrame()
    {
        frame = new JFrame("Gadget Shop");
        Container contentPane = frame.getContentPane();

        //MAIN PANEL
        //This will contain 3 panels:
        //welcomePanel at the top for a welcome message.
        //attributesPanel on the left to add items to the list.
        //gadgetUsagePanel on the right to interact with the items of the list.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        contentPane.add(mainPanel);

        //Create a scroll pane and add it to the content pane.
        //This will ensure all components remain at their original positions even if the user reduces the window size to below the original size.
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        contentPane.add(scrollPane);

        //Create gridbag constraints for panels added directly onto the main panel
        GridBagConstraints mainGbc = new GridBagConstraints();
        //Create gridbag constraints for panels nested on another panel.
        GridBagConstraints gbc = new GridBagConstraints();

        //ATTRIBUTES PANEL
        //This will contain the text fields and buttons needed to add gadgets to gadgetsArrayList.
        JPanel attributesPanel = createPanel(220, 350);
        mainGbc.gridx = 0;
        mainGbc.gridy = 1;
        mainPanel.add(attributesPanel,mainGbc);

        attributesPanel.add(Box.createRigidArea(new Dimension(20,0)));

        JLabel modelLabel = new JLabel("Model:");
        attributesPanel.add(modelLabel);

        textFieldModel = createTextField();
        attributesPanel.add(textFieldModel);

        JLabel priceLabel = new JLabel("Price:");
        attributesPanel.add(priceLabel);

        textFieldPrice = createTextField();
        attributesPanel.add(textFieldPrice);

        JLabel weightLabel = new JLabel("Weight:");
        attributesPanel.add(weightLabel);

        textFieldWeight = createTextField();
        attributesPanel.add(textFieldWeight);

        JLabel sizeLabel = new JLabel("Size:");
        attributesPanel.add(sizeLabel);

        textFieldSize = createTextField();
        attributesPanel.add(textFieldSize);

        attributesPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel creditLabel = new JLabel("Credit:");
        attributesPanel.add(creditLabel);

        textFieldCredit = createTextField();
        attributesPanel.add(textFieldCredit);

        addMobileButton = createButton("Add Mobile");
        attributesPanel.add(addMobileButton);

        attributesPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel memoryLabel = new JLabel("Memory:");
        attributesPanel.add(memoryLabel);

        textFieldMemory = createTextField();
        attributesPanel.add(textFieldMemory);

        addMP3Button = createButton("Add MP3");
        attributesPanel.add(addMP3Button);

        //GADGET USAGE PANEL (located to the right of attributesPanel)
        //Create a panel that will store displayNumber, MP3, Mobile, and Utilities panels. 
        //This allows me to be more dynamic with the positioning of certain components.
        JPanel gadgetUsagePanel = new JPanel(new GridBagLayout());
        mainGbc.gridx = 1;
        mainGbc.gridy = 1;
        mainPanel.add(gadgetUsagePanel, mainGbc);

        //DISPLAY NUMBER PANEL
        //Panel containing "Display Nr" label and text box.
        JPanel displayNumberPanel = createPanel(200,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gadgetUsagePanel.add(displayNumberPanel, gbc);

        JLabel displayNrLabel = new JLabel("Display Number:");
        displayNumberPanel.add(displayNrLabel);

        textFieldDisplayNr = createTextField();
        displayNumberPanel.add(textFieldDisplayNr);

        //MP3 PANEL
        //Panel containing "file size" label and text box, as well as "download music" and "delete music" buttons.
        JPanel mp3Panel = createPanel(220,160);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gadgetUsagePanel.add(mp3Panel, gbc);

        mp3Panel.add(Box.createRigidArea(new Dimension(20,0)));

        JLabel fileSizeLabel = new JLabel("File size:");
        mp3Panel.add(fileSizeLabel);

        textFieldFileSize = createTextField();
        mp3Panel.add(textFieldFileSize);

        mp3Panel.add(Box.createRigidArea(new Dimension(0, 8)));

        downloadMusicButton = createButton("Download music");
        mp3Panel.add(downloadMusicButton);

        mp3Panel.add(Box.createRigidArea(new Dimension(0,8)));

        deleteMusicButton = createButton("Delete music");
        mp3Panel.add(deleteMusicButton);

        //MOBILE PANEL
        //Create a panel containing the "duration" and "phone number" labels, their respective text boxes, and the make call button.
        JPanel mobilePanel = createPanel(220,160);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gadgetUsagePanel.add(mobilePanel, gbc);

        mobilePanel.add(Box.createRigidArea(new Dimension(20,0)));

        JLabel durationLabel = new JLabel("Duration:");
        mobilePanel.add(durationLabel);

        textFieldDuration = createTextField();
        mobilePanel.add(textFieldDuration);

        JLabel phoneNrLabel = new JLabel("Phone Nr:");
        mobilePanel.add(phoneNrLabel);

        textFieldPhoneNr = createTextField();
        mobilePanel.add(textFieldPhoneNr);

        makeCallButton = createButton("Make call");
        mobilePanel.add(makeCallButton);

        //UTILITY PANEL
        //Create a panel containing the "sell gadget", "clear", and "display all" buttons.
        JPanel utilityPanel = new JPanel();
        utilityPanel.setLayout(new BoxLayout(utilityPanel, BoxLayout.Y_AXIS));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gadgetUsagePanel.add(utilityPanel,gbc);

        sellGadgetButton = createButton("Sell gadget");
        utilityPanel.add(sellGadgetButton);

        utilityPanel.add(Box.createRigidArea(new Dimension(0,10)));

        clearButton = createButton("Clear");
        utilityPanel.add(clearButton);

        utilityPanel.add(Box.createRigidArea(new Dimension(0,10)));

        displayAllButton = createButton("Display all");
        utilityPanel.add(displayAllButton);

        utilityPanel.add(Box.createRigidArea(new Dimension(0,30)));

        //WELCOME PANEL
        //Panel that displays a welcome message.
        JPanel welcomePanel = new JPanel();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.gridwidth = 2;
        mainPanel.add(welcomePanel, mainGbc);

        JLabel welcomeLabel = new JLabel("Welcome to my Gadget Shop!");
        welcomeLabel.setFont(new Font("Helvetica",Font.BOLD,30));
        welcomePanel.add(welcomeLabel);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}