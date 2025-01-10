// Register
document.getElementById("registerForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const email = document.getElementById("registerEmail").value;
    const password = document.getElementById("registerPassword").value;

    try {
        const response = await fetch("http://localhost:8080/auth/register", {
            method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify({email, password}),
        });

        if (response.ok) {
            document.getElementById("registerResult").innerText = "Registration successful!";
        } else {
            const error = await response.text();
            document.getElementById("registerResult").innerText = `Error: ${error}`;
        }
    } catch (err) {
        document.getElementById("registerResult").innerText = "Error: " + err.message;
    }
});

// Login
document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;

    try {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify({email, password}),
        });

        if (response.ok) {
            document.getElementById("loginResult").innerText = "Login successful!";
        } else {
            document.getElementById("loginResult").innerText = "Invalid credentials!";
        }
    } catch (err) {
        document.getElementById("loginResult").innerText = "Error: " + err.message;
    }
});
