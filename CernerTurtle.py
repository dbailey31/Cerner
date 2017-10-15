import turtle

t = turtle.Turtle()
t.color('lightgreen')
t.write("Cerner", move=False, align="left", font=("Arial", 12, "normal"))

t.penup()
t.right(90)
t.forward(45)
t.left(90)
t.forward(25)
t.pendown()

t.color('lightblue')
for i in range(180):
    t.forward(2)
    t.left(2)
