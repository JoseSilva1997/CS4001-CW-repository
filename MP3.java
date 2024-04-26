import java.util.*;

class MP3 extends Gadget
{
    private int memory;
    //Added an extra attribute to represent the maximum amount of memory held by the MP3. This will
    //be used to ensure the user can never exceed this number.
    private int capacity;

    public MP3(String model, double price, int weight, String size, int space)
    {
        super(model, price, weight, size);
        memory = space;
        capacity = space;
    }

    public int getMemory()
    {
        return memory;
    }

    public int getCapacity()
    {
        return capacity;
    }
    
    /**
     * Simulates downloading a song by inputting its size and deducting it from the available memory.
     * Verifies that the file size is a positive number that does not exceed the amount of available memory.
     * Displays error messages if those conditions are not met.
     */
    public void downloadSong(int songSize)
    {
        if(songSize > memory) {
            System.out.println("Not enough Space. Please delete some songs");
        }
        else if(songSize < 0) {
            System.out.println("Not a valid file size");
        }
        else {
            memory -= songSize;
            System.out.println("Download completed!\nAvailable space: " + memory + " of " + capacity + " MB");
        }
    }

    /**
     * Simulates deleting a song by inputting its size.
     * Verifies that the size given is a positive number, and that it doesn't exceed the capacity of the MP3 when added to the amount of available memory.
     * Displays error messages if those conditions are not met.
     */
    public void deleteSong(int songSize)
    {
        if(memory + songSize > capacity) {
            System.out.println("Memory cannot exceed max capacity");
        }
        else if(songSize < 0) {
            System.out.println("Not a valid file size");
        }
        else {
            memory += songSize;
            System.out.println("Deletion completed!\nAvailable space: " + memory + " of " + capacity + " MB");
        }
    }

    /**
     * Display the MP3's information on the terminal in a suitably annotated manner.
     */
    public void print()
    {
        super.print();
        System.out.println("Space: "+ memory+ " of "+capacity+" MB\n ");
    }
}

