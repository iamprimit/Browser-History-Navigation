# Browser-History-Navigation
Stack-based browser history simulation in C. Supports visit(), back(n), forward(n), go(n), and showHistory(). Demonstrates how real browsers manage navigation using back and forward stacks.

A lightweight C project that simulates how real web browsers manage navigation history using stack-based logic.  
It replicates essential browser operations such as visiting new pages, going back/forward (single or multiple steps), and displaying full navigation history. Inspired by the JavaScript History API.

---

## Features

### Core Navigation
- `visit(url)` – open a new page  
- `back()` / `back(n)` – move backward through history  
- `forward()` / `forward(n)` – move forward through history  
- `go(n)` – jump directly (negative = back, positive = forward)  
- `showHistory()` – display full stack-based history view  

### Extended Functionality
- Multi-step movement similar to `history.go(n)`
- Clears forward stack on new visits (like real browsers)
- Tracks current page and stack contents
- Uses two independent stacks to model browser navigation

### History Visualization
```
-3. example.com
-2. site.com
-1. pageA.com
0. currentPage.com  <– you are here
1. pageB.com
2. pageC.com
```
Back history = negative numbers  
Forward history = positive numbers  

---

## Project Structure

```
/BrowserHistory
├── browser_history.c      // Main source code
├── README.md              // Project documentation
└── LICENSE (optional)
```
---

## How It Works

The system uses **two stacks**:

- **backStack** → for previously visited pages  
- **forwardStack** → for pages ahead of the current location  

Browser-like behavior is achieved by moving pages between these stacks:

| Action        | backStack              | forwardStack            | currentPage     |
|---------------|-------------------------|--------------------------|------------------|
| visit(url)    | push current            | cleared                  | url              |
| back(n)       | pop n → current         | push previous currents   | popped element   |
| forward(n)    | pop n → current         | push previous currents   | popped element   |

---

## Example Terminal Session

```
=== Browser ===
Current Page: google.com
1. Visit New Page
2. Back
3. Forward
4. Show History
5. Exit
```
The program supports full interactive navigation with real-time history updates.

---

## Why This Project?

This project helps learners understand:

- How browser navigation logically works  
- How stacks model backward/forward history  
- How APIs like `history.back()`, `history.forward()`, and `history.go()` behave  
- How to design and debug stack-based systems  

Great for beginners in **C programming**, **Data Structures**, or **browser internals**.

---

## Inspiration

Inspired by the **Web History API**, which:

> “Exposes useful methods that let you navigate back and forth through the user's history, and manipulate the contents of the history stack.”

This project recreates similar behavior in pure C.

---

Feel free to open issues or submit pull requests!  
Improvements, optimizations, and new features are welcome.

