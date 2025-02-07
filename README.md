Step#1: Set up DeepSeek & Ollama
Ollama
Ollama is a platform that allows running and managing AI models locally, including DeepSeek. Follow these steps to install and run Ollama.

Install Ollama: Follow below steps to install Ollama:
Download the installer from Ollama download page. Ollama is available for Windows, macOS, and Linux. Select the link accordingly.
Run the downloaded ‘.exe’ file and follow the on-screen installation instructions. It has very simple & straightforward steps to install.
Run & Verify Ollama Installation: Follow below steps to run & verify Ollama:
To run Ollama, open a terminal (such as cmd in Windows) and run a command: > Ollama serve 
To verify Ollama, open a separate terminal and run a command: > Ollama –version
DeepSeek
Ollama is a platform that allows running and managing AI models locally, including DeepSeek. Follow these steps to install and run Ollama.

Install DeepSeek: Follow below steps to install DeepSeek:
Make sure that Ollama is running as per aforementioned steps. Open a new terminal and run a command : > ollama pull deepseek-r1:latest 
Check for the completion percentage of installation in the terminal. It will display a successful installation message once 100% completes.
Verify & Run DeepSeek: Follow below steps to run & verify DeepSeek:
To verify all the installed versions of DeepSeek, run a command in the same terminal: > Ollama list
To run DeepSeek, run a command in the same terminal: > ollama run deepseek-r1:latest
Once it runs successfully, you will see a message “Send a message (/? for help)”. It means you can send your prompt & get response from there itself.
Step#2: Set Up a Spring Boot Starter Project
Create a Spring Boot Project:
Use Spring Initializr to generate a Spring Boot project.
Add the following dependencies:
Spring Web (for MVC/REST APIs)
Ollama (to run DeepSeek locally)
Thymeleaf(for user interface)
Import the Project:
Import the generated project into your IDE.
Step#3: Update application.properties file

Add below entry to application.proprties:

spring.ai.ollama.chat.options.model=deepseek-r1:latest
Step#4: Create a Controller

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeepSeekChatController {

    private final ChatClient chatClient;

    public DeepSeekChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/")
    public String showForm() {
        return "chat-form"; // This points to chat-form.html in src/main/resources/templates/
    }

    @PostMapping("/sendPrompt")
    public String processPrompt(@RequestParam("chat") String chat, Model model) {
        try {
            String response = chatClient.prompt(chat).call().content();
            model.addAttribute("chat", chat);
            model.addAttribute("response", response);
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "chat-form";
    }
}

Step#5: ‘Add chat-form.html’ for user interface

Add a Thymeleaf template ‘chat-form.html’ in src/main/resources/templates/:




<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>

<title>DeepSeek AI Chat</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">

<style>

body {

background: linear-gradient(135deg, #667eea, #764ba2);

height: 100vh;

display: flex;

align-items: center;

justify-content: center;

}

.chat-container {

width: 550px;

height: 400px;

padding: 20px;

background: #e6f7ff;

border-radius: 15px;

box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.3);

display: flex;

flex-direction: column;

justify-content: space-between;

}

.form-title {

font-size: 1.8rem;

font-weight: bold;

color: mediumblue;

text-align: center;

margin-bottom: 20px; 

}

.form-label {

font-size: 1.5rem;

font-weight: normal;

}

.input-group {

display: flex;

align-items: center;

}

.form-control {

font-size: 1.2rem;

padding: 8px;

flex: 1;

overflow: auto;

}

.btn-send {

background: linear-gradient(135deg, #007bff, #0056b3);

border: none;

color: white;

font-size: 2rem;

width: 50px;

height: 50px;

border-radius: 50%;

display: flex;

align-items: center;

justify-content: center;

margin-left: 10px;

transition: background 0.3s ease-in-out, transform 0.2s;

}

.btn-send:hover {

background: linear-gradient(135deg, #0056b3, #007bff);

transform: scale(1.1);

}

textarea {

font-size: 1.4rem;

height: 120px;

resize: none;

color: blue;

}

body, .form-title, .form-label, .form-control, .btn-send, textarea {

font-family: 'Arial Nova', sans-serif; 

}

</style>

</head>

<body>

<div class="chat-container">

<h2 class="form-title">DeepSeek Integration<br>With Java Spring Boot Application</h2> 

<form th:action="@{/sendPrompt}" method="post">

<div class="mb-2">

<label for="chat" class="form-label">Enter your prompt:</label>

<div class="input-group">

<input type="text" id="chat" name="chat" class="form-control" placeholder="Ask me anything..." required>

<button type="submit" class="btn btn-send">⬆</button>

</div>

</div>

</form>

<hr>

<label class="form-label text-secondary">Response:</label>

<textarea id="response" class="form-control" readonly th:text="${response}"></textarea>

<p class="text-danger mt-2" th:text="${error}"></p>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>

</body>

</html>


Step#6: Test the Integration
Run the Spring Boot Application:
Start your Spring Boot application.
Test the Integration API:
Enter any query on prompt field, click the arrow button at the right.
Wait for a while & observe the response in the response field.
Verify closely the response from DeepSeek.

Step#7: Optional Enhancements
You are hereHome > DeepSeek >
DeepSeek Spring AI Integration Using Java Spring Boot
DeepSeek java Spring Spring AI Spring Boot Spring Boot 3 by devs5003 - February 6, 20250
DeepSeek Spring AI IntegrationDeepSeek is a smart AI tool that helps computers understand and respond to human language. It behaves like a digital assistant that can answer questions, write text, or even generate code! Whether you want to build a chatbot, automate customer support, or create an app that “talks” to users, DeepSeek provides the brains to make it happen. It acts like a super-smart friend who is always ready to help with words, ideas, or solutions.

Integrating DeepSeek (or any custom AI model) with a Spring AI project involves several steps. In this article we will go through a step-by-step tutorial on ‘DeepSeek Spring AI Integration Using Java Spring Boot’. We will explore how to connect DeepSeek locally with a Spring AI project and test the chat response.


Table of Contents
What is DeepSeek?
Why DeepSeek is Better Than Other Prominent AI Models?
Displays it’s Thinking on All Aspects of Query Before Responding
Superior Performance in Mathematical & Logical Reasoning
Cost-Effective & Open-Source Friendly
Optimized for Long-Context Understanding
Offline Availability Without Internet Dependency
Efficient Fine-Tuning & Customization
Stronger Context Awareness & Response Relevance
Better Integration with AI Platforms & Local Deployment via Ollama
Strong Support for Code Generation & Debugging
Future-Proof AI Model with Continuous Upgrades
Ethical AI & Bias Reduction
What is Ollama?
Why Integrate DeepSeek with Java Spring Boot?
DeepSeek Spring AI Integration Using Java Spring Boot
Prerequisites
Step#1: Set up DeepSeek & Ollama
Ollama
DeepSeek
Step#2: Set Up a Spring Boot Starter Project
Step#3: Update application.properties file
Step#4: Create a Controller
Step#5: ‘Add chat-form.html’ for user interface
Step#6: Test the Integration
Step#7: Optional Enhancements
Project Structure
ADVERTISEMENT

Robopet Make Your Robot Follow You 

00:00/00:00

What is DeepSeek?
DeepSeek is an advanced AI tool targeted for natural language processing (NLP) tasks such as chat-based interactions, content generation, and AI-powered assistance. It is developed to offer high-quality responses by understanding context, generating human-like text, and adapting to various prompts appropriately. DeepSeek is widely used in applications like chatbots, customer support automation, and AI-driven content creation.

Why DeepSeek is Better Than Other Prominent AI Models?
DeepSeek stands out in the AI landscape due to its unique features and optimizations. Below are some key points explaining why DeepSeek is considered superior to other prominent AI models in the early days of it’s release:

Displays it’s Thinking on All Aspects of Query Before Responding
DeepSeek displays how it thinks internally about all aspects of the user’s query and then provides the best possible response to the user. This feature makes it an impressive tool amongst it’s users.

Superior Performance in Mathematical & Logical Reasoning
DeepSeek is designed with enhanced mathematical and logical reasoning capabilities. It is trained on technical datasets, therefore it is considered as more effective for coding, problem-solving, and analytical tasks. It outperforms many AI models in structured thinking and complex computations.

Since it focuses more on technical tasks like coding, math, and logical reasoning, it may become a go-to solution provider for developers and engineers. Other models (e.g., ChatGPT) are more general-purpose and might struggle with highly technical queries.

Cost-Effective & Open-Source Friendly
DeepSeek offers high performance at a lower cost with better resource efficiency, that makes it more accessible for startups, researchers, projects on a budget, and businesses as compared to some proprietary AI models like GPT-4.

It supports open-source integration for developers to modify and extend its capabilities without being locked into a vendor ecosystem.

Optimized for Long-Context Understanding
Unlike some AI models that struggle with retaining long conversations, DeepSeek is fine-tuned to handle extensive context, ensuring better memory retention and more accurate responses over long interactions. It processes requests quickly, even for complex tasks, ensuring minimal wait times.

Offline Availability Without Internet Dependency

Unlike cloud-only models (e.g., Gemini), DeepSeek can run locally via Ollama to ensure data privacy and offline availability. We can run DeepSeek locally via Ollama to ensure faster response times and offline AI processing without internet dependency.

Efficient Fine-Tuning & Customization
DeepSeek provides better adaptability and fine-tuning options, allowing developers to train the model for specific use cases without needing excessive computational resources. This makes it a great choice for industry-specific applications like healthcare, finance, and legal AI solutions.

Stronger Context Awareness & Response Relevance
Many AI models generate responses that lack deep context awareness, but DeepSeek is optimized to provide highly relevant and accurate responses based on previous interactions and contextual hints. This leads to more natural, human-like conversations.

Better Integration with AI Platforms & Local Deployment via Ollama
DeepSeek seamlessly integrates with Spring AI, Ollama, and other AI deployment frameworks. It facilitates smooth local execution and enhanced privacy compared to cloud-based models like Gemini or ChatGPT.

Strong Support for Code Generation & Debugging
DeepSeek excels at code generation, debugging, and explanation, making it a powerful tool for developers. It understands complex programming concepts and provides optimized, well-structured code snippets in various programming languages.

Future-Proof AI Model with Continuous Upgrades

DeepSeek’s development team is actively improving the model, adding new features, optimizations, and better inference mechanisms to stay ahead of the competition.

Ethical AI & Bias Reduction
DeepSeek is trained with strong bias mitigation strategies, making it less prone to generating misleading or biased outputs compared to some other AI models. This ensures a more responsible and fair AI experience for users.

What is Ollama?
Ollama is a free, easy-to-use powerful tool to run AI models locally on our system without relying on cloud-based services. It simplifies the process of downloading, managing, and interacting with AI models like DeepSeek. It ensures fast and efficient processing directly on our local machine.

Instead of relying on internet-based services (which can be slow or expensive), Ollama allows us to work with AI tools offline. Imagine downloading a smart robot to our laptop—it works fast, keeps our data private, and doesn’t require an internet connection. Ollama is perfect for experimenting with AI, testing ideas, or building apps without worrying about cloud costs or delays.

Why Integrate DeepSeek with Java Spring Boot?
Developers can build intelligent applications that leverage AI-driven responses in real-time by integrating DeepSeek with Spring Boot using Spring AI. Whether it’s for chat applications, automated assistants, or data-driven decision-making, combining DeepSeek with Spring Boot provides a scalable and efficient way to enhance user experiences with AI.

In the next section, we will explore DeepSeek Spring AI Integration with Java Spring Boot, step by step, to create a simple yet powerful AI-powered application.


DeepSeek Spring AI Integration Using Java Spring Boot
Prerequisites
Java Development Kit (JDK): Ensure you have JDK 17 or later installed.
Spring Boot: Familiarity with Spring Boot and Spring AI.
DeepSeek Model: Ensure you have the DeepSeek model set up locally (e.g., via an API or a local server).
Build Tool: Maven or Gradle for dependency management.
IDE: IntelliJ IDEA, Eclipse, STS, or any preferred IDE.
Step#1: Set up DeepSeek & Ollama
Ollama
Ollama is a platform that allows running and managing AI models locally, including DeepSeek. Follow these steps to install and run Ollama.

Install Ollama: Follow below steps to install Ollama:
Download the installer from Ollama download page. Ollama is available for Windows, macOS, and Linux. Select the link accordingly.
Run the downloaded ‘.exe’ file and follow the on-screen installation instructions. It has very simple & straightforward steps to install.
Run & Verify Ollama Installation: Follow below steps to run & verify Ollama:
To run Ollama, open a terminal (such as cmd in Windows) and run a command: > Ollama serve 
To verify Ollama, open a separate terminal and run a command: > Ollama –version
DeepSeek
Ollama is a platform that allows running and managing AI models locally, including DeepSeek. Follow these steps to install and run Ollama.

Install DeepSeek: Follow below steps to install DeepSeek:
Make sure that Ollama is running as per aforementioned steps. Open a new terminal and run a command : > ollama pull deepseek-r1:latest 
Check for the completion percentage of installation in the terminal. It will display a successful installation message once 100% completes.
Verify & Run DeepSeek: Follow below steps to run & verify DeepSeek:
To verify all the installed versions of DeepSeek, run a command in the same terminal: > Ollama list
To run DeepSeek, run a command in the same terminal: > ollama run deepseek-r1:latest
Once it runs successfully, you will see a message “Send a message (/? for help)”. It means you can send your prompt & get response from there itself.
Step#2: Set Up a Spring Boot Starter Project
Create a Spring Boot Project:
Use Spring Initializr to generate a Spring Boot project.
Add the following dependencies:
Spring Web (for MVC/REST APIs)
Ollama (to run DeepSeek locally)
Thymeleaf(for user interface)
Import the Project:
Import the generated project into your IDE.
Step#3: Update application.properties file

Add below entry to application.proprties:

spring.ai.ollama.chat.options.model=deepseek-r1:latest
Step#4: Create a Controller

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeepSeekChatController {

    private final ChatClient chatClient;

    public DeepSeekChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/")
    public String showForm() {
        return "chat-form"; // This points to chat-form.html in src/main/resources/templates/
    }

    @PostMapping("/sendPrompt")
    public String processPrompt(@RequestParam("chat") String chat, Model model) {
        try {
            String response = chatClient.prompt(chat).call().content();
            model.addAttribute("chat", chat);
            model.addAttribute("response", response);
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "chat-form";
    }
}

Step#5: ‘Add chat-form.html’ for user interface

Add a Thymeleaf template ‘chat-form.html’ in src/main/resources/templates/:




<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>

<title>DeepSeek AI Chat</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">

<style>

body {

background: linear-gradient(135deg, #667eea, #764ba2);

height: 100vh;

display: flex;

align-items: center;

justify-content: center;

}

.chat-container {

width: 550px;

height: 400px;

padding: 20px;

background: #e6f7ff;

border-radius: 15px;

box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.3);

display: flex;

flex-direction: column;

justify-content: space-between;

}

.form-title {

font-size: 1.8rem;

font-weight: bold;

color: mediumblue;

text-align: center;

margin-bottom: 20px; 

}

.form-label {

font-size: 1.5rem;

font-weight: normal;

}

.input-group {

display: flex;

align-items: center;

}

.form-control {

font-size: 1.2rem;

padding: 8px;

flex: 1;

overflow: auto;

}

.btn-send {

background: linear-gradient(135deg, #007bff, #0056b3);

border: none;

color: white;

font-size: 2rem;

width: 50px;

height: 50px;

border-radius: 50%;

display: flex;

align-items: center;

justify-content: center;

margin-left: 10px;

transition: background 0.3s ease-in-out, transform 0.2s;

}

.btn-send:hover {

background: linear-gradient(135deg, #0056b3, #007bff);

transform: scale(1.1);

}

textarea {

font-size: 1.4rem;

height: 120px;

resize: none;

color: blue;

}

body, .form-title, .form-label, .form-control, .btn-send, textarea {

font-family: 'Arial Nova', sans-serif; 

}

</style>

</head>

<body>

<div class="chat-container">

<h2 class="form-title">DeepSeek Integration<br>With Java Spring Boot Application</h2> 

<form th:action="@{/sendPrompt}" method="post">

<div class="mb-2">

<label for="chat" class="form-label">Enter your prompt:</label>

<div class="input-group">

<input type="text" id="chat" name="chat" class="form-control" placeholder="Ask me anything..." required>

<button type="submit" class="btn btn-send">⬆</button>

</div>

</div>

</form>

<hr>

<label class="form-label text-secondary">Response:</label>

<textarea id="response" class="form-control" readonly th:text="${response}"></textarea>

<p class="text-danger mt-2" th:text="${error}"></p>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>

</body>

</html>

Step#6: Test the Integration
Run the Spring Boot Application:
Start your Spring Boot application.
Test the Integration API:
Enter any query on prompt field, click the arrow button at the right.
Wait for a while & observe the response in the response field.
Verify closely the response from DeepSeek.
Step#7: Optional Enhancements

We may further enhance other capabilities as per our requirements. Some of them are:

Error Handling:
Add error handling in the DeepSeekService to manage API failures.
Logging:
Add logging to track requests and responses.
Configuration:
Check the DeepSeek with other configurations to application.properties.
Security:
Secure your API endpoints using Spring Security if needed.
