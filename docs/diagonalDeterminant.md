## Diagonal Determinant for 3x3 matrices

---
unfortunately this method only works for 3x3 matrices, but it solves them in n^2 time
Author: Nathan Mcconnell

    public  static double determinant(Matrix m) {
        int size =  m.getNumRow();

        double rightSide = 0;
        double leftSide = 0;
        boolean virginty = true;

        for(int i = 0; i < (2*size - 1); i++) {
            if (i <= size - 1) {
                rightSide += getDiagonal(i, m, i, true);
            }
            if (i >= size - 1) {
                if(virginty) {
                    System.out.println("Left Diagonals");
                    virginty = false;
                }
                int statCol = i;
                if (i > size - 1) {
                    statCol -= size;
                }
                leftSide += getDiagonal(statCol, m, i, false);
            }
        }

        System.out.println("rightSide = " + rightSide +  " leftSide = " + leftSide);
        System.out.println("solution: " + (rightSide - leftSide));
        return rightSide - leftSide;
    }

    private static double getDiagonal(int startCol, Matrix m, int i, boolean direction) {
        int index = startCol;
        int incrementValue = 1;
        if(!direction){
            incrementValue = -1;
        }
        int count = incrementValue;
        ArrayList<Integer> list = new ArrayList<>();
        double results = 1;
        for(int j = 0; j < m.getNumCol(); j++) {
            double value = m.getData()[j][index];
            list.add(Integer.valueOf((int) value));
            results *= value;
            index = i + count;
            count +=  incrementValue;
            if(index > m.getNumCol() - 1) {
                index = index -  m.getNumCol();
            }
        }
        System.out.print("[");
        list.forEach(k ->{System.out.print(k + ", ");});

        System.out.println("] Result: " + results);

        return results;
    }