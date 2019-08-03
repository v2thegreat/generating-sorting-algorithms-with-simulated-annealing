//
// Created by aliab on 12-Feb-19.
//

#include "SimulatedAnnealing.h"

float SimulatedAnnealing::calcTemp(int k, int runSteps) {
    return (float) k/runSteps;
}
