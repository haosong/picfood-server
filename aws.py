from flask import Flask, request
from subprocess import call

app = Flask(__name__)

@app.route('/',methods=['POST'])
def foo():
    call(["./aws.sh"])
    return "Deployed\n"

if __name__ == '__main__':
    app.run()
