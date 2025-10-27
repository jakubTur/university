<<<<<<< HEAD
import requests
=======
import argparse
import smtplib
import requests
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from bs4 import BeautifulSoup
from datetime import datetime
from dotenv import load_dotenv
import os

load_dotenv()

EMAIL = os.getenv("EMAIL")
PASSWORD = os.getenv("PASSWORD")

def send_email(message):
    now = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    subject = f"Lab message sent on {now}"
    msg = MIMEMultipart()
    msg['From'] = EMAIL
    msg['To'] = EMAIL
    msg['Subject'] = subject
    msg.attach(MIMEText(message, 'plain'))
    try:
        with smtplib.SMTP_SSL('smtp.gmail.com', 465) as server:
            server.login(EMAIL, PASSWORD)
            server.send_message(msg)
        print("Email sent successfully.")
    except Exception as e:
        print(f"Failed to send email: {e}")

def get_facts(n):
    url = "https://api.chucknorris.io/jokes/random"
    print(f"Fetching {n} Chuck Norris fact(s):")
    for _ in range(n):
        try:
            response = requests.get(url)
            joke = response.json().get("value")
            print("-", joke)
        except Exception as e:
            print("Error fetching fact:", e)

def get_teachers(letter):
    url = f"https://wit.pwr.edu.pl/en/faculty/structure/employees?letter={letter.upper()}"
    headers = {"User-Agent": "Mozilla/5.0"}
    try:
        response = requests.get(url, headers=headers)
        soup = BeautifulSoup(response.text, 'html.parser')
        people = soup.select(".news-box")
        if not people:
            print(f"No teachers found with last name starting with '{letter.upper()}'.")
            return
        print(f"\nThe list of researchers - {letter.upper()}\n")
        for person in people:
            name_tag = person.select_one("a.title")
            name = name_tag.get_text(strip=True) if name_tag else "No name"
            email_tag = person.find("p")
            email = email_tag.get_text(strip=True).replace("E-mail: ", "") if email_tag else "No email"
            print(f"{name} - {email}")
    except Exception as e:
        print("Error scraping page:", e)
>>>>>>> 59e354d70bba261163c277f8864878100c62b955

def foo():
    print("This is the lab assignment solution")
    parser = argparse.ArgumentParser(description="Lab application")
    parser.add_argument("--mail", type=str, help="Send email with this message")
    parser.add_argument("--facts", type=int, help="Number of facts to retrieve")
    parser.add_argument("--teachers", type=str, help="Initial letter of teacher's last name")
    args = parser.parse_args()
    if args.mail:
        send_email(args.mail)
    elif args.facts is not None:
        get_facts(args.facts)
    elif args.teachers:
        get_teachers(args.teachers)
    else:
        s = input("Enter something: ")
        print(f"Data read from input: {s}")

foo()
