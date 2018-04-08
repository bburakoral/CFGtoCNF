
import Business.ContextFreeGrammarTools;
import Business.EpsilonProduction;
import Business.Printer;
import Model.ContextFreeGrammar;
import Model.State;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        Printer printer = new Printer();
        ContextFreeGrammar epsilonProduction = new EpsilonProduction().getEpsilonProduction();
        ContextFreeGrammarTools contextFreeGrammarTools = new ContextFreeGrammarTools();

        System.out.println("Before  Eliminate Epsilon-Production");
        printer.printCFG(epsilonProduction);
        ContextFreeGrammar epsilonProductionResult = contextFreeGrammarTools.EliminateEpsilonProduction(epsilonProduction);
        System.out.println("\n\n");
        System.out.println("After Eliminate Epsilon-Production ");

         printer.printCFG(epsilonProductionResult);



    }

}
