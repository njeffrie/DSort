public Interface clientInterface{

    //reads from a File object (of ASCII encoded space sperarated integers)
    //returns array of integers
    int[] readFile(File f);

    //creates a file (as a File object)
    //writes String of space seperated ints to file
    void writeFile(String str);

    //breaks up unsorted int array into parts and distributes to other machines
    void distribute();
}
