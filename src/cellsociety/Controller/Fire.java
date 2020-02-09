package cellsociety.Controller;

import java.util.*;

import javafx.scene.paint.Color;

public class Fire extends Simulation {

    private List<String> defaultNeighbors =  List.of("N","S","E","W");
    private int square = 4;
    private int defaultShape = square;
    private int finite = 1;
    private int defaultEdge = finite;

    private int empty = 0;
    private int tree = 1;
    private int burning = 2;

    private static final float PROB_CATCH = .15F;
    private static final float PROB_GROW = .15F;

    @Override
    public void updateGrid() {
        if (!simulationGrid.isNeighborhoodSet()) {
            simulationGrid.setNeighbors(defaultNeighbors, defaultShape, defaultEdge);
        }
        for(int r = 0; r < simulationGrid.getSize(); r ++) {
            for (int c = 0; c < simulationGrid.getSize(); c++) {
                simulationGrid.checkNeighbors(r, c, true);
                if(simulationGrid.getReferenceState(r,c)==burning) {
                    simulationGrid.updateCell(r,c,empty);
                } else if (simulationGrid.getReferenceState(r,c)==tree && catchesFire(r,c)) {
                    simulationGrid.updateCell(r,c,burning);
                } else if (simulationGrid.getReferenceState(r,c)==empty) { // What do I do if cell is empty state?
                    if (growsTree(r,c)) {
                        simulationGrid.updateCell(r, c, tree);
                    } else {
                        simulationGrid.updateCell(r, c, empty);
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(simulationGrid.getGrid()));
    }

    @Override
    public int getSimulationCols() {
        return GRID_WIDTH;
    }

    @Override
    public Map<Integer, Color> getCellColorMap() {
        return cellColorMap;
    }

    @Override
    protected void init() {
        cellColorMap = new HashMap<>();
        cellColorMap.put(empty, Color.WHITE);
        cellColorMap.put(tree, Color.GREEN);
        cellColorMap.put(burning, Color.ORANGE);
    }

    private boolean catchesFire(int r, int c) {
        Map<String, Integer> statusOfNeighbors = simulationGrid.checkNeighbors(r,c,true);
        int burn = 0;
        for (String neighbor : statusOfNeighbors.keySet()) {
            if(statusOfNeighbors.get(neighbor) == burning){
                burn++;
            }
        }
        if (burn != 0) {
            Random rand = new Random();
            float float_random = rand.nextFloat();
            if (float_random < PROB_CATCH) {
                return true;
            }
        }
        return false;
    }

    private boolean growsTree(int r, int c) {
        Random rand = new Random();
        float float_random = rand.nextFloat();
        if (float_random < PROB_GROW) {
            return true;
        }
        return false;
    }


}
