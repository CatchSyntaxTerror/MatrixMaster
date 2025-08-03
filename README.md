# 🧮 MatrixMaster

*An interactive Linear Algebra calculator built with Java and JavaFX.*

MatrixMaster is your toolkit for performing basic matrix math to eigenvalue domination.

---

## ✨ Features

- ➕ Matrix addition, subtraction, multiplication, inversion  
- 🔽 Row reduction (RREF)  
- 🧠 Determinant calculation  
- 🧬 Eigenvalues and eigenvectors  
- 🧭 Vector operations (dot product, cross product, normalization, etc.)  
- ✅ Input validation and clean result formatting  
- 🎛️ JavaFX GUI with operation selection and responsive input prompts

---

## 🚀 How to Run

MatrixMaster is distributed as a runnable `.jar` file. You’ll need **Java 17+** and **JavaFX 21** installed.

### 💻 Launch Command: (comming soon)

\`\`\`bash
java --module-path /path/to/javafx-sdk-21/lib \
     --add-modules javafx.controls,javafx.fxml \
     -jar MatrixMaster.jar
\`\`\`

> 🔧 Replace `/path/to/javafx-sdk-21/lib` with the actual path to your JavaFX SDK's `lib` folder.

---

## 🗂️ Project Structure

- `main/`: Entry point (`Main.java`)  
- `gui/`: JavaFX controllers & user interface logic  
- `operations/`: Core linear algebra logic (matrices, vectors, eigenstuff)  
- `utils/`: Input parsing, formatting, validation  
- `model/`: Matrix and Vector wrapper classes  
- `resources/`: JavaFX assets (`style.css`, `layout.fxml`)

---

## 📋 TODO List

### 🔨 Classes to Implement

- [x] `Matrix`  
- [ ] `Vector` (started)  
- [x] `MatrixOperations`  
- [ ] `VectorOperations`  
- [ ] `EigenSolver`  
- [ ] `InputController`  
- [ ] `MainMenuController` (started)  
- [ ] `InputValidator`  
- [ ] `MatrixFormatter`  
- [ ] `MatrixParser`  
- [ ] `AlertHelper` (started)

### 🧮 Buttons to Implement

- [x] Add Matrices  
- [x] Subtract Matrices  
- [x] Multiply Matrices  
- [x] Inverse Matrix  
- [x] Determinant  
- [x] RREF  
- [ ] Eigenvalues  
- [ ] Eigenvectors  
- [ ] Dot Product  
- [ ] Cross Product  
- [ ] Normalize Vector  
- [ ] Scalar Multiply  
- [x] Exit

### 🖼️ Windows to Design

- [x] Main Menu Screen  
- [ ] Add Matrices Screen  
- [ ] Subtract Matrices Screen  
- [ ] Multiply Matrices Screen  
- [ ] Inverse Matrix Screen  
- [ ] Determinant Screen  
- [ ] RREF Screen  
- [ ] Eigenvalues Screen  
- [ ] Eigenvectors Screen  
- [ ] Dot Product Screen  
- [ ] Cross Product Screen  
- [ ] Normalize Vector Screen  
- [ ] Scalar Multiply Screen

---

## 🌱 Possible Additions

- [ ] Complex number support  
- [ ] Screenshots of the GUI in action 📸

---

## 👤 Author

**Youssef Amin**  
> Lifelong dot product enthusiast.  
> GitHub: [CatchSyntaxTerror](https://github.com/CatchSyntaxTerror)
