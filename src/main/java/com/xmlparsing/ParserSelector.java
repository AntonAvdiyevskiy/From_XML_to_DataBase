package com.xmlparsing;


public class ParserSelector {
    private Parser parser = null;
    public  Parser getParser(int choice){
        boolean rightChoice=true;
        while(rightChoice){
            switch (choice) {
                case 1:
                    parser=new DomParser();
                    rightChoice=false;
                    break;
                case 2:
                    parser=new SaxParser();
                    rightChoice=false;
                    break;
                case 3:
                    parser=new StAXParser();
                    rightChoice=false;
                    break;

                default:
                    System.out.println("enter 1,2 or 3 to choose parser");
                    rightChoice=false;
                    break;
            }
        }
        return  parser;
    }
}
