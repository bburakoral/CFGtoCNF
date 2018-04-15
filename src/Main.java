
import Business.ContextFreeGrammarTools;
import Model.*;
import Business.Printer;

public class Main {

    public static void main(String[] args){

        Printer printer = new Printer();
        ContextFreeGrammar epsilonProduction = new EpsilonProduction().getEpsilonProduction();
        ContextFreeGrammarTools contextFreeGrammarTools = new ContextFreeGrammarTools();
        ContextFreeGrammar uselessSymbol = new UselessSymbol().getContextFreeGrammar();
        ContextFreeGrammar unitProduction = new UniteProduction().getContextFreeGrammar();
        

        System.out.println("Before  Eliminate Epsilon-Production");
        printer.printCFG(epsilonProduction);
        ContextFreeGrammar epsilonProductionResult = contextFreeGrammarTools.eliminateEpsilonProduction(epsilonProduction);

        System.out.println("\n");
        System.out.println("After Eliminate Epsilon-Production ");
        printer.printCFG(epsilonProductionResult);


        System.out.println("\n");
        System.out.println("Before Eliminate Unite Production");
        printer.printCFG(unitProduction);


        System.out.println("\n");
        System.out.println("After Eliminate Unite Production");
        ContextFreeGrammar unitProductionResult = contextFreeGrammarTools.eliminateUnitProduction(unitProduction);
        printer.printCFG(unitProductionResult);


        System.out.println("\n");
        System.out.println("Before Eliminate Useless Symbols");
        printer.printCFG(uselessSymbol);

       ContextFreeGrammar uselessSymbolResult = contextFreeGrammarTools.eliminateUselessSymbol(uselessSymbol);
        System.out.println("\n\n");
        System.out.println("After Eliminate Useless Symbols");
        printer.printCFG(uselessSymbolResult);



        ContextFreeGrammar CFGtoCNF = new CFGtoCNF().getContextFreeGrammar();

        System.out.println("\n");
        System.out.println("Before Convert CFG to CNF");
        printer.printCFG(CFGtoCNF);

        System.out.println("\n");
        System.out.println("After Convert CFG to CNF");
        ContextFreeGrammar resultOfConverter = contextFreeGrammarTools.CFGtoCNF(CFGtoCNF);
        printer.printCFG(resultOfConverter);




    }

}
