package com.netcracker.transportation.runners;

import com.netcracker.transportation.algorithms.Solution;
import com.netcracker.transportation.algorithms.TransportationProblem;
import com.netcracker.transportation.algorithms.TransportationProblemSolver;

import java.util.Map;

import static com.netcracker.transportation.utils.GeneralUtils.toLinkedMap;
import static com.netcracker.transportation.utils.ProblemSupplier.createProblemMap;
import static com.netcracker.transportation.utils.SolverSupplier.createSolverMap;
import static com.netcracker.transportation.utils.io.ResultPrinter.printResults;

public class DefaultRunner {

    public static void run() {

        // Available transportation problems
        Map<String, TransportationProblem> problemMap = createProblemMap();

        // Available transportation problem solvers
        Map<String, TransportationProblemSolver> solverMap = createSolverMap();

        // Find solution for each transportation problem using each solver
        Map<String, Map<String, Solution>> allSolutions = findSolutionForEveryProblem(
                problemMap,
                solverMap
        );

        // Output
        printResults(allSolutions);
    }

    public static Map<String, Map<String, Solution>> findSolutionForEveryProblem(Map<String, TransportationProblem> problemMap,
                                                                                 Map<String, TransportationProblemSolver> solverMap) {
        return problemMap
                .entrySet()
                .stream()
                .collect(toLinkedMap(
                        Map.Entry::getKey,
                        problemEntry -> findSolutionUsingMultipleSolvers(problemEntry.getValue(), solverMap)
                ));
    }

    public static Map<String, Solution> findSolutionUsingMultipleSolvers(TransportationProblem problem,
                                                                         Map<String, TransportationProblemSolver> solverMap) {
        return solverMap
                .entrySet()
                .stream()
                .collect(toLinkedMap(
                        Map.Entry::getKey,
                        solverEntry -> findSolutionUsingOneSolver(problem, solverEntry.getValue())
                ));
    }

    public static Solution findSolutionUsingOneSolver(TransportationProblem problem,
                                                      TransportationProblemSolver solver) {
        return solver.getSolution(problem);
    }
}
