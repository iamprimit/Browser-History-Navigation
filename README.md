# Browser-History-Navigation
Stack-based browser history simulation in C. Supports visit(), back(n), forward(n), go(n), and showHistory(). Demonstrates how real browsers manage navigation using back and forward stacks.

A lightweight and educational C project that simulates how real web browsers manage navigation history.
It uses two stacks to replicate browser-like operations such as:
	•	visit(url)
	•	back() / back(n)
	•	forward() / forward(n)
	•	go(n)
	•	showHistory()

The project is inspired by the JavaScript History API, which exposes methods for moving through and manipulating the browser’s session history.

⸻

Features

Core Navigation
	•	Visit new pages
	•	Back navigation (single-step or multi-step)
	•	Forward navigation (single-step or multi-step)

Extended Functionality
	•	history.go(n) style movement (negative = back, positive = forward)
	•	Stack-based history storage
	•	Accurate clearing of forward history when visiting a new page
	•	Current page tracking

⸻

Visual History View

Use showHistory() to display the simulated browser history:

-3. example.com
-2. site.com
-1. pageA.com
 0. currentPage.com  <-- you are here
 1. pageB.com
 2. pageC.com

Back = negative
Forward = positive

Project Structure

/BrowserHistory
│
├── browser_history.c      // Main source code
├── README.md              // Project documentation
└── LICENSE (optional)     // If you choose to add one

⸻

How It Works

The browser history is implemented using two stacks:
	•	backStack → pages behind the current page
	•	forwardStack → pages ahead of the current page

Operations move pages between these stacks:

Action	backStack	forwardStack	currentPage
visit(url)	push current	clear	url
back(n)	pop n to current	push each previous current	last popped
forward(n)	pop n to current	push each previous current	last popped


⸻

Example Usage

=== Browser ===
Current Page: google.com
1. Visit New Page
2. Back
3. Forward
4. Show History
5. Exit

Supports interactive navigation and history exploration.

⸻

Why This Project?

This project is designed to help students and developers understand:
	•	How browsers manage navigation history internally
	•	How stack-based navigation works
	•	How multi-step movement (history.go(n)) is implemented
	•	How back/forward interactions modify history

Perfect for those learning Data Structures, C programming, or browser internals.

⸻

Inspiration

This project takes inspiration from the Web History API, which exposes methods allowing backward and forward navigation, as well as manipulating the session history stack.

“It exposes useful methods and properties that let you navigate back and forth through the user’s history, and manipulate the contents of the history stack.”

⸻

License

You can add any license you prefer: MIT, Apache 2.0, GPL, etc.

⸻

Contributions

Pull requests, suggestions, and improvements are welcome!
