from django.shortcuts import render
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse,request
from django.shortcuts import render,redirect
import random,datetime
# Create your views here.
from  .models import faculty,student,salary,login,course,batch,enquiry,career,archiement,review,studymaterial,chat,attendence,exam,syllabus,work,application,facultyattedence
def logins(request):
    if request.method == 'POST':
        username = request.POST['uname']
        password = request.POST['pwd']
        print("ppp")

        if login.objects.filter(username=username, password=password).exists():
            print("fff")
            yy = login.objects.get(username=username, password=password)

            request.session["lid"]=yy.id
            request.session["uname"] = username
            if yy.user_type == "admin":
                return render(request, "admin/admin_hom.html")





        else:
            return HttpResponse('<script>alert("Invalid username or password");window.location=""</script>')
    else:

        print("jjjj")
        return render(request,"login.html")

def login_post(request):
    print("hhhh")
    username=request.POST['uname']
    password=request.POST['pwd']
    if request.method=='POST':
        print("jk")
        if login.objects.get(username=username,password=password,user_type='admin').exists():
            yy=login.objects.get(username=username,password=password)
            return render(request,"admin/admin_hom.html")
        else:
            return HttpResponse('<script>alert("Invalid username or password");window.location=""</script>')
    return render(request,"admin/login.html")

def admin_home(request):
    return render(request ,"admin/admin_hom.html")


def abc(request):
    return render(request,"admin_index.html")

def admin_add_batch_post(request):
    if request.method=="POST":

        batchname=request.POST['textfield']
        batchyear=request.POST['select']
        batchmonth=request.POST['select2']
        cc=request.POST['select3']
        b=batch()
        b.batch_name=batchname
        b.batch_year=batchyear
        b.batch_month=batchmonth
        b.COURSE=course.objects.get(pk=cc)
        b.save()


        return render(request,"admin/add_batch.html")

    else:
        c = course.objects.all()
        return render(request, "admin/add_batch.html", {'c': c})



def admin_edit_batch(request,id):
    c = course.objects.all()
    d=batch.objects.get(pk=id)

    return render(request,"admin/edit_batch.html",{ 'c' : c,'d': d  })

def admin_edit_batch_post(request):
    batchname = request.POST['textfield']
    batchyear = request.POST['select']
    batchmonth = request.POST['select2']
    course = request.POST['select3']

    return render(request,"admin/edit_batch.html")






def admin_add_career_post(request):
    if request.method=="POST":

        title=request.POST['textfield']
        discription=request.POST['textfield2']
        file=request.POST['file']
        lastdate=request.POST['textfield3']

        cr=career()
        cr.title=title
        cr.discription=discription
        cr.file=file
        cr.lastdate=lastdate
        cr.save()
        return render(request,"admin/add_carrier.html")
    else:
        return render(request, "admin/add_carrier.html")













def admin_edit_career(request):
    return render(request,"admin/edit_carrier.html")

def admin_edit_career_post(request):
    title = request.POST['textfield']
    discription = request.POST['textfield2']
    file = request.POST['file']
    lastdate = request.POST['textfield3']
    return render(request,"admin/edit_carrier.html")







def admin_add_course_post(request):
    if request.method=="POST":

        coursename=request.POST['textfield']
        discription = request.POST['textarea']
        duration = request.POST['textfield2']
        amount=request.POST['textfield3']

        cs=course()
        cs.course_name=coursename
        cs.discription=discription
        cs.duration=duration
        cs.amount=amount
        cs.save()
        return render(request,"admin/add_course.html")
    else:
        return render(request, "admin/add_course.html")



# def admin_edit_course(request):
#     return render(request,"admin/edit_course.html")
#
# def admin_edit_course_post(request):
#     coursename = request.POST['textfield']
#     discription = request.POST['textarea']
#     duration = request.POST['textfield2']
#     return render(request,"admin/edit_course.html")





def admin_add_faculty_post(request):
    if request.method=="POST":

        name=request.POST['textfield']
        gender=request.POST['radiobutton']
        email=request.POST['textfield2']
        phonenumber=request.POST['textfield3']
        image=request.FILES['file']
        fs=FileSystemStorage()
        sa=fs.save(image.name,image)
        url=fs.url(sa)
        print("hai")

        housename=request.POST['textfield4']
        place=request.POST['textfield5']
        post=request.POST['textfield6']
        pin=request.POST['textfield7']
        batch_id=request.POST['select']
        ll=login()
        ll.username=email
        ll.password="hhh"
        ll.user_type="faculty"
        ll.save()
        print("yyy")
        yy = login.objects.get(username=email, password="hhh")
        print("oooooo")
        fa=faculty()
        fa.facultyname=name
        fa.gender=gender
        fa.email=email
        fa.phone_no=phonenumber
        fa.image=url
        fa.house_name=housename
        fa.place=place
        fa.post=post
        fa.pin=pin
        print("ppppss=",batch_id)
        qq=batch.objects.get(pk=batch_id)
        print("qsss")
        p=login.objects.get(pk=yy.id)
        print("p=",p)
        fa.LOGIN=p
        print("qqq22")


        fa.BATCH = qq
        fa.save()

        return render(request,"admin/add_faculty.html")
    else:
        res = batch.objects.all()
        return render(request, "admin/add_faculty.html", {'c': res})



def admin_edit_faculty(request):
    return render(request,"admin/edit_faculty.html")


def admin_edit_faculty_post(request):
    name = request.POST['textfield']
    gender = request.POST['radiobutton']
    email = request.POST['textfield2']
    phonenumber = request.POST['textfield3']
    image = request.FILES['file']
    housename = request.POST['textfield4']
    place = request.POST['textfield5']
    post = request.POST['textfield6']
    pin = request.POST['textfield7']
    batch = request.POST['select']

    return render(request,"admin/edit_faculty.html")






def admin_add_faculty_attendence(request):
    return render(request,"admin/add_faculty_atttendence.html")



def admin_add_salary(request):
    return render(request,"admin/add_salary.html")

def admin_add_salary_post(request,id):
    if request.method=="POST":
        return render(request,"admin/add_salary.html")




def admin_edit_salary(request):
    return render(request,"admin/edit_salary.html")


def admin_edit_salary_post(request):

    salary = request.POST['textfield3']
    return render(request,"admin/edit_salary.html")







def admin_add_student_post(request):
    if request.method=="POST":

        name=request.POST['textfield']
        gender=request.POST['radiobutton']
        dob=request.POST['textfield2']
        image=request.FILES['file']
        fs = FileSystemStorage()
        sa = fs.save(image.name, image)
        url = fs.url(sa)


        email=request.POST['textfield3']
        phonnumber=request.POST['textfield4']
        housename=request.POST['textfield5']
        place=request.POST['textfield6']
        post=request.POST['textfield7']
        pin=request.POST['textfield8']
        # course=request.POST['select']
        batch_id=request.POST['select2']

        ll = login()
        ll.username = email
        ll.password = "hhh"
        ll.user_type = "student"
        ll.save()
        yy = login.objects.get(username=email, password="hhh")



        sa=student()
        sa.student_name=name
        sa.gender=gender
        sa.email=email
        sa.phone_no=phonnumber
        sa.dob=dob
        sa.image=url
        sa.housename=housename
        sa.place=place
        sa.post=post
        sa.pin=pin
        p = login.objects.get(pk=yy.id)

        sa.LOGIN = p
        sa.BATCH=batch.objects.get(pk=batch_id)

        sa.save()
        return render(request,"admin/add_student.html")
    else:
        res=batch.objects.all()
        res1=course.objects.all()
        return render(request, "admin/add_student.html",{"batch": res,"course":res1})


def admin_edit_student(request):
    return render(request,"admin/edit_student.html")


def admin_edit_student_post(request):
    name = request.POST['textfield']
    gender = request.POST['radiobutton']
    dob = request.POST['textfield2']
    image = request.FILES['file']
    email = request.POST['textfield3']
    phonnumber = request.POST['textfield4']
    housename = request.POST['textfield5']
    place = request.POST['textfield6']
    post = request.POST['textfield7']
    pin = request.POST['textfield8']
    course = request.POST['select']
    batch = request.POST['select2']
    return render(request,"admin/edit_student.html")










def admin_view_batch(request):
    c = batch.objects.all()
    return render(request, "admin/view_batch.html", {'c': c})




def admin_view_batch_search(request):
    search=request.POST['textfield']
    c=batch.objects.filter(batch_name__contains=search)
    return render(request,"admin/view_batch.html",{'c' : c})



def admin_view_batch_del(request,id):
    res = batch.objects.get(id=id)
    res.delete()

    c = batch.objects.all()
    return render(request, "admin/view_batch.html", {'c': c})

def admin_view_batch_edit(request,id,bname):
    print("kkk")
    res=batch.objects.all()
    print(res)
    print("ppp")
    request.session['bid']=id
    request.session['bname'] = bname
    cc = course.objects.all()
    bb=bname
    return render(request, "admin/edit_batch.html",{'c': res,'cc':cc,'bb':bb})

def admin_batch_update(request):
    print("ff")
    bname=request.POST['textfield']
    byear=request.POST['select']
    bmonth=request.POST['select2']
    cname=request.POST['select3']
    print("cn=",cname)
    idz=request.session['bid']
    print("jjjj")
    qq=course.objects.get(id=cname)
    print("pppp")
    obj=batch.objects.get(pk=idz)

    obj.batch_name=bname
    obj.batch_year=byear
    obj.batch_month=bmonth
    print("pww")
    obj.COURSE_id=cname


    print("qqqq")
    print(idz)
    qry=batch.objects.filter(pk=idz).update(batch_name=bname,batch_year=byear,batch_month=bmonth,COURSE=qq)

    c = batch.objects.all()
    return render(request, "admin/view_batch.html", {'c': c})






def admin_view_course_edit(request,id):
    res=course.objects.get(pk=id)
    print(res)
    request.session['cupid']=id
    return render(request, "admin/edit_course.html",{'c':res})


def admin_course_update(request):
    coursename=request.POST['textfield']
    discription=request.POST['textarea']
    duration=request.POST['textfield2']
    amount=request.POST['textfield3']
    idz=request.session['cupid']
    qry=course.objects.filter(pk=idz).update(course_name=coursename,discription=discription,duration=duration,amount=amount)
    return redirect("myapp:admin_view_course")




def admin_view_career(request):
    c=career.objects.all()
    return render(request,"admin/view_carrier.html", {'c': c})


def admin_view_course(request):
    c = course.objects.all()
    return render(request,"admin/view_course.html", {'c' : c})

def admin_view_course_search(request):
    search=request.POST['textfield']
    c = course.objects.filter(course_name__contains=search)
    return render(request,"admin/view_course.html", {'c' : c})







def admin_view_course_del(request,id):
    print("jjj")
    res=course.objects.get(id=id)
    res.delete()
    print("jjjj222")
    # return HttpResponse("jjjj22")

    c = course.objects.all()
    return render(request, "admin/view_course.html", {'c': c})

def admin_view_faculty(request):
    c=faculty.objects.all()
    return render(request,"admin/view_faculty.html",{'c': c})


def admin_view_faculty_edit(request,id):
    res=faculty.objects.get(pk=id)
    request.session['fid']=id
    return render(request, "admin/edit_faculty.html",{'c': res})

def admin_faculty_update(request):
    fname=request.POST['textfield']
    gender=request.POST['radiobutton']
    email=request.POST['textfield2']
    phonnum=request.POST['textfield3']
    image=request.FILES['file']
    fs=FileSystemStorage()
    sa=fs.save(image.name, image)
    url=fs.url(sa)

    house=request.POST['textfield4']
    place=request.POST['textfield5']
    post=request.POST['textfield6']
    pin=request.POST['textfield7']
    bname=request.POST['select']



    idz=request.session['ffid']
    qq=batch.objects.get(id=bname)
    obj=faculty.objects.get(pk=idz)
    obj.facultyname=fname
    obj.gender=gender
    obj.email=email
    obj.phone_no=phonnum
    obj.image=url
    obj.house_name=house
    obj.place=place
    obj.post=post
    obj.pin=pin
    obj.BATCH_id=qq
    qry=faculty.objects.filter(pk=idz).update(facultyname=fname,gender=gender,email=email,phone_no=phonnum,image=image,house_name=house,place=place,post=post,pin=pin,BATCH=qq)
    c=faculty.objects.all()
    return  render(request,"admin/view_faculty.html",{'c': c})







def admin_view_faculty_del(request,id):
    res=faculty.objects.get(id=id)
    res.delete()
    c=faculty.objects.all()
    return render(request, "admin/view_faculty.html", {'c': c})





def admin_view_student(request):
    c = student.objects.all()
    return render(request,"admin/view_student.html",{'c': c})



def admin_view_archievment(request):
    c=archiement.objects.all()
    return render(request,"admin/view_archievment.html",{'c': c})



def admin_view_enquery(request):
    c=enquiry.objects.all()
    return render(request,"admin/view_enquery.html",{'c': c})




def admin_view_review(request):
    c=review.objects.all()
    return render(request,"admin/view_review.html",{'c': c})

def admin_view_career_applicant(request):
    c=application.objects.all()
    return render(request,"admin/view_carrier_applicant.html",{'c': c})




def admin_view_salary(request):
    c=salary.objects.all()
    return render(request,"admin/view_salary.html",{'c': c})








def admin_view_attendence(request):
    return render(request,"admin/view_attendence.html")







