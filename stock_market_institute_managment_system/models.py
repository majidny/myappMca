from django.db import models

# Create your models here.

class login(models.Model):
    username=models.CharField(max_length=100)
    password=models.CharField(max_length=100)
    user_type=models.CharField(max_length=100)
    class Meta:
        db_table='login'


class course(models.Model):
    course_name=models.CharField(max_length=100)
    discription=models.CharField(max_length=100)
    duration=models.CharField(max_length=100)
    amount=models.CharField(max_length=100)

    class Meta:
        db_table = 'course'

class batch(models.Model):
    batch_name=models.CharField(max_length=100)
    batch_year=models.CharField(max_length=100)
    batch_month=models.CharField(max_length=100)
    COURSE=models.ForeignKey(course,on_delete=models.CASCADE)
    class Meta:
        db_table='batch'


class faculty(models.Model):
    LOGIN=models.ForeignKey(login,on_delete=models.CASCADE)
    facultyname=models.CharField(max_length=100)
    gender=models.CharField(max_length=100, default="male")
    email=models.CharField(max_length=100)
    phone_no=models.CharField(max_length=100)
    image=models.FileField(max_length=200)
    house_name=models.CharField(max_length=100)
    place=models.CharField(max_length=100)
    post=models.CharField(max_length=100)
    pin=models.CharField(max_length=100)
    BATCH=models.ForeignKey(batch,on_delete=models.CASCADE)
    class Meta:
        db_table='faculty'


class student(models.Model):
    LOGIN=models.ForeignKey(login,on_delete=models.CASCADE)
    student_name=models.CharField(max_length=100)
    gender=models.CharField(max_length=100)
    email=models.CharField(max_length=100)
    phone_no=models.CharField(max_length=100)
    housename=models.CharField(max_length=100)
    place=models.CharField(max_length=100)
    post=models.CharField(max_length=100)
    pin=models.CharField(max_length=100)
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    dob=models.CharField(max_length=100)
    gender=models.CharField(max_length=100)
    image=models.FileField(max_length=100)
    class Meta:
        db_table='student'


class enquiry(models.Model):
    date=models.CharField(max_length=100)
    name=models.CharField(max_length=100)
    phone_no=models.CharField(max_length=100)
    discrption=models.CharField(max_length=100)
    email=models.CharField(max_length=100)

    class Meta:
        db_table = 'enquiry'

class career(models.Model):
    date=models.CharField(max_length=100)
    title=models.CharField(max_length=100)
    discription=models.CharField(max_length=100)
    file=models.CharField(max_length=100)
    lastdate=models.CharField(max_length=100)

    class Meta:
        db_table = 'career'

class archiement(models.Model):
    date=models.CharField(max_length=100)
    STUDENT= models.ForeignKey(student, on_delete=models.CASCADE)
    file=models.FileField(max_length=100)
    discription=models.CharField(max_length=100)
    status=models.CharField(max_length=100)

    class Meta:
        db_table = 'archiement'

class review(models.Model):
    COURSE=models.ForeignKey(course,on_delete=models.CASCADE)
    date=models.CharField(max_length=100)
    STUDENT= models.ForeignKey(student,on_delete=models.CASCADE)
    review=models.CharField(max_length=100)
    class Meta:
        db_table = 'review'


class studymaterial(models.Model):
    title=models.CharField(max_length=100)
    file=models.FileField(max_length=100)
    date=models.FileField(max_length=100)
    COURSE=models.ForeignKey(course,on_delete=models.CASCADE)
    FACULTY=models.ForeignKey(faculty,on_delete=models.CASCADE)
    class Meta:
         db_table = 'studymaterial'

class chat(models.Model):
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE)
    type=models.CharField(max_length=100)
    date=models.CharField(max_length=100)
    message=models.CharField(max_length=100)
    class Meta:
         db_table = 'chat'

class attendence(models.Model):
    date=models.CharField(max_length=100)
    STUDENT=models.ForeignKey(student,on_delete=models.CASCADE)
    status=models.CharField(max_length=100)

    class Meta:
         db_table = 'attendence'


class exam(models.Model):
    title=models.CharField(max_length=100)
    starting_date=models.CharField(max_length=100)
    BATCH=models.ForeignKey(batch,on_delete=models.CASCADE)
    discrption=models.CharField(max_length=100)

    class Meta:
         db_table = 'exam'

class syllabus(models.Model):

    COURSE=models.ForeignKey(course,on_delete=models.CASCADE)
    title=models.CharField(max_length=100)
    file=models.FileField(max_length=100)

    class Meta:
         db_table = 'syllabus'


class work(models.Model):
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    discrption = models.CharField(max_length=100)
    date = models.CharField(max_length=100)
    FACULTY = models.ForeignKey(faculty,on_delete=models.CASCADE)
    class Meta:
        db_table = 'work'


class application(models.Model):

    STUDENT= models.ForeignKey(student,on_delete=models.CASCADE)
    date = models.CharField(max_length=100)
    CAREER = models.ForeignKey(career,on_delete=models.CASCADE)
    class Meta:
        db_table = 'application'

class salary(models.Model):


    salary = models.CharField(max_length=100)
    FACULTY = models.ForeignKey(faculty,on_delete=models.CASCADE)
    class Meta:
        db_table = 'salary'


class facultyattedence(models.Model):
    date = models.CharField(max_length=100)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE)
    class Meta:
        db_table = 'facultyattedence'



