class Gadget
{
    private String model;
    private double price;
    private int weight;
    private String size;

    public Gadget(String model1, double price1, int weight1, String size1)
    {
        model = model1;
        price = price1;
        weight = weight1;
        size = size1;
    }
    
    public String getModel()
    {
        return model;
    }

    public double getPrice()
    {
        return price;
    }

    public int getWeight()
    {
        return weight;
    }

    public String getSize()
    {
        return size;
    }
    
    /**
     * Print the gadget's details to the terminal in a suitably annotated manner.
     */
    public void print()
    {   
        System.out.println("Model: "+model);
        System.out.println("Price: £"+price);
        System.out.println("Weight: "+weight+"grams");
        System.out.println("Size: "+ size);
    }
    
}