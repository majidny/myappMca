from django.shortcuts import render
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse, request, JsonResponse
from django.shortcuts import render,redirect
import random,datetime
# Create your views here.
from  .models import faculty,student,salary,login,course,batch,enquiry,career,archiement,review,studymaterial,chat,attendence,exam,syllabus,work,application,facultyattedence,batchassign
def logins(request):
    if request.method == 'POST':
        username = request.POST['unamee']
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

    return render(request,"admin/admin_hom.html")



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


def admin_view_batch(request):
    c = batch.objects.all()
    return render(request, "admin/view_batch.html", {'c': c})

def admin_view_batch_search(request):
    search=request.POST['textfield']
    c=batch.objects.filter(batch_name__icontains=search)
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








def admin_add_career_post(request):
    if request.method=="POST":

        title=request.POST['textfield']
        discription=request.POST['textfield2']
        file=request.FILES['file']
        fs = FileSystemStorage()
        sa = fs.save(file.name, file)
        url = fs.url(sa)

        lastdate=request.POST['textfield3']

        cr=career()
        cr.title=title
        cr.discription=discription
        cr.file=url
        cr.lastdate=lastdate
        cr.date=datetime.datetime.now().date()
        cr.save()
        return render(request,"admin/add_carrier.html")
    else:
        return render(request, "admin/add_carrier.html")



def admin_edit_career(request,id):
    c=career.objects.get(id=id)
    request.session['crrid'] = id

    return render(request,"admin/edit_carrier.html",{'c': c})

def admin_edit_career_post(request):
    title = request.POST['textfield']
    discription = request.POST['textfield2']
    file = request.POST['file']
    lastdate = request.POST['textfield3']
    return render(request,"admin/edit_carrier.html")



def admin_view_career(request):
    c=career.objects.all()
    return render(request,"admin/view_carrier.html", {'c': c})


def admin_view_career_edit(request,id):
    res=career.objects.get(pk=id)

    return render(request,"admin/edit_carrier.html", {'c': res})

def admin_view_career_update(request):
    print("hlw")
    title=request.POST['textfield']
    description=request.POST['textfield2']
    lastdate=request.POST['textfield3']

    print("hai")
    idz= request.session['crrid']
    print("id=",idz)
    cr=career.objects.get(id=idz)
    cr.title=title
    cr.discription=description
    if 'file' in request.FILES:
        img=request.FILES['file']
        fs=FileSystemStorage()
        sa=fs.save(img.name, img)
        url=fs.url(sa)
        cr.img=url

    cr.lastdate=lastdate
    cr.save()
    c=career.objects.all()
    return render(request,"admin/view_carrier.html",{'c': c})



def admin_view_career_search(request):
    search=request.POST['textfield']
    c = career.objects.filter(title__icontains=search)
    return render(request,"admin/view_carrier.html", {'c' : c})


def admin_view_career_del(request,id):
    res=career.objects.get(id=id)
    res.delete()
    c=career.objects.all()
    return render(request, "admin/view_carrier.html", {'c': c})






def admin_add_course_post(request):
    if request.method=="POST":

        coursename=request.POST['textfield']
        discription = request.POST['textarea']


        file = request.FILES['file']
        fs = FileSystemStorage()
        sa = fs.save(file.name, file)
        url = fs.url(sa)

        duration = request.POST['textfield2']
        amount=request.POST['textfield3']

        cs=course()
        cs.course_name=coursename
        cs.discription=discription
        cs.file=url
        cs.duration=duration
        cs.amount=amount
        cs.save()
        return render(request,"admin/add_course.html")
    else:
        return render(request, "admin/add_course.html")










def admin_add_faculty_post(request):
    if request.method=="POST":

        name=request.POST['textfield']
        gender=request.POST['radiobutton']
        email=request.POST['textfield2']
        phonenumber=request.POST['textfield3']
        qualification=request.POST['qualification']
        experince=request.POST['experince']

        image=request.FILES['file']
        fs=FileSystemStorage()
        sa=fs.save(image.name,image)
        url=fs.url(sa)
        print("hai")

        housename=request.POST['textfield4']
        place=request.POST['textfield5']
        post=request.POST['textfield6']
        pin=request.POST['textfield7']
        # batch_id=request.POST['select']
        password=random.randint(1000,9999)
        ll=login()
        ll.username=email
        ll.password=str(password)
        ll.user_type="faculty"
        ll.save()

        fa=faculty()
        fa.facultyname=name
        fa.gender=gender
        fa.email=email

        fa.phone_no=phonenumber
        fa.qualifiction=qualification
        fa.experience=experince
        fa.image=url
        fa.house_name=housename
        fa.place=place
        fa.post=post
        fa.pin=pin
        # print("ppppss=",batch_id)
        # qq=batch.objects.get(pk=batch_id)
        print("qsss")

        fa.LOGIN=ll
        print("qqq22")


        # fa.BATCH = qq
        fa.save()

        # import smtplib
        # from email.mime.multipart import MIMEMultipart
        # from email.mime.text import MIMEText
        #
        # import smtplib
        # s = smtplib.SMTP(host='smtp.gmail.com', port=587)
        # s.starttls()
        # s.login("majidny22@gmail.com", "M@jid@212121")
        # msg = MIMEMultipart()  # create a message.........."
        # message = "New password"
        # msg['From'] = "majidny22@gmail.com"
        # msg['To'] = email
        # msg['Subject'] = "Your Password"
        # body = "Your Username is your mail and Password is:- - " + str(password)
        # msg.attach(MIMEText(body, 'plain'))
        # s.send_message(msg)

        return render(request,"admin/add_faculty.html")
    else:
        res = batch.objects.all()
        return render(request, "admin/add_faculty.html", {'c': res})












def admin_edit_faculty(request):
    cc = course.objects.all()


    return render(request,"admin/edit_faculty.html")













def admin_edit_faculty_post(request):
    name = request.POST['textfield']
    gender = request.POST['radiobutton']
    email = request.POST['textfield2']
    phonenumber = request.POST['textfield3']
    qualification = request.POST['qualification']
    experince = request.POST['experince']


    image = request.FILES['file']
    housename = request.POST['textfield4']
    place = request.POST['textfield5']
    post = request.POST['textfield6']
    pin = request.POST['textfield7']
    # batch = request.POST['select']

    return render(request,"admin/edit_faculty.html")






def admin_add_faculty_attendence(request):

    return render(request,"admin/add_faculty_atttendence.html")



def admin_add_salary(request,id):
    res = faculty.objects.get(pk=id)
    request.session['fac_id']=id
    ss = salary.objects.filter(FACULTY=res)
    if ss.exists():
        ss=ss[0]
        sal=ss.salary
    else:
        sal="0"
    return render(request,"admin/add_salary.html",{'c': res,"sal":sal})

def admin_add_salary_post(request):
    if request.method=="POST":
        sal=request.POST['textfield3']

        year=request.POST['select1']
        month=request.POST['select2']

        print(year,month)


        id=request.session['fac_id']
        res = faculty.objects.get(pk=id)
        ss=salary.objects.filter(FACULTY=res,year=year,month=month)
        if ss.exists():
            ss=ss[0]
            ss.salary=sal
            ss.year=year
            ss.month=month
            ss.save()
        else:
            s=salary()
            s.salary=sal
            s.year = year
            s.month = month


            s.FACULTY=res
            s.save()
        c = faculty.objects.all()
        return render(request, "admin/view_faculty.html", {'c': c})



def admin_edit_salary(request):
    c=salary.objects.all()
    return render(request,"admin/edit_salary.html")


def admin_edit_salary_post(request):

    salary = request.POST['textfield3']
    return render(request,"admin/edit_salary.html")

def admin_search_salary(request):

    year=request.POST['select1']
    month=request.POST['select2']
    print(month)
    print(year)

    c = salary.objects.filter(year=year,month=month)

    print(c)

    return render(request, "admin/view_salary.html", {'c': c})










def ajax_batch_by_crs(request,cid):
    print(cid)
    # cid=request.POST["cid"]
    course_obj=course.objects.get(id=cid)
    batch_obj=batch.objects.filter(COURSE=course_obj)
    data=[]
    for ii in batch_obj:
        ss={'id':ii.id,"bname":ii.batch_name}
        data.append(ss)
    return JsonResponse({'status':'ok','data':data})


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
        password = random.randint(1000, 9999)
        ll = login()
        ll.username = email
        ll.password = str(password)
        ll.user_type = "student"
        ll.save()


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

        sa.LOGIN = ll
        sa.BATCH=batch.objects.get(pk=batch_id)

        sa.save()
        return render(request,"admin/add_student.html")
    else:
        # res=batch.objects.all()
        res1=course.objects.all()
        return render(request, "admin/add_student.html",{"course":res1})
        # return render(request, "admin/add_student.html",{"batch": res,"course":res1})

def admin_batch_assignn(request):
    if request.method=="POST":

        facullty1 = request.POST['nn']
        batch1=request.POST['select2']


        # datee=request.POST['textfield2']
        qq=batchassign()
        qq.FACULTY_id=facullty1
        qq.BATCH_id=batch1

        # qq.FACULTY=faculty.objects.get(pk=)
        #
        # qq.BATCH = batch.objects.get(pk=)
        qq.date = datetime.datetime.now().date()
        qq.save()
        return render(request,"admin/admin_hom.html")



    else:
        fac = faculty.objects.all()
        res1 = course.objects.all()

        return render(request, "admin/bacth_assignn.html", {"course": res1,'ff':fac})



def admin_edit_student(request,id):
    d=student.objects.get(id=id)
    e=course.objects.all()
    f=batch.objects.all()
    request.session['sssid'] = id
    return render(request,"admin/edit_student.html",{'d':d,'e':e,'f': f})


def admin_edit_student_post(request):
    print("haii")
    id=request.session['sssid']
    d = student.objects.get(id=id)
    name = request.POST['textfield']
    gender = request.POST['radiobutton']
    dob = request.POST['textfield2']
    email = request.POST['textfield3']
    phonnumber = request.POST['textfield4']
    housename = request.POST['textfield5']
    place = request.POST['textfield6']
    post = request.POST['textfield7']
    pin = request.POST['textfield8']
    coursez = request.POST['select']
    print("crz")
    print(coursez)
    batchz = request.POST['select2']
    res1=course.objects.get(pk=coursez)
    res=batch.objects.get(pk=batchz,COURSE=res1)
    res3=batch.objects.get(pk=res.id)

    print("batch")
    print(res)
    if 'fileField' not  in request.FILES:
        print("not")
        d.student_name=name
        d.gender=gender
        d.dob=dob
        d.email=email
        d.phone_no=phonnumber
        d.housename=housename
        d.place=place
        d.post=post
        d.pin=pin
        d.BATCH=res3

        d.save()
        return redirect("myapp:admin_view_student")
    else:
        print("maybe")
        image = request.FILES['fileField']
        if image=="":
            print("null")
            d.student_name = name
            d.gender = gender
            d.dob = dob
            d.email = email
            d.phone_no = phonnumber
            d.housename = housename
            d.place = place
            d.post = post
            d.pin = pin
            d.BATCH = res3

            d.save()
            return render(request, "admin/edit_student.html")
        else:
            print("yz")
            image = request.FILES['fileField']
            fs = FileSystemStorage()
            sa = fs.save(image.name, image)
            url = fs.url(sa)
            d.image = url
            d.student_name = name
            d.gender = gender
            d.dob = dob
            d.email = email
            d.phone_no = phonnumber
            d.housename = housename
            d.place = place
            d.post = post

            d.BATCH =res3






            d.save()
            return render(request, "admin/edit_student.html")


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


    if 'file' in request.FILES:
        img=request.FILES['file']
        fs=FileSystemStorage()
        sa=fs.save(img.name, img)
        url=fs.url(sa)

        course.objects.filter(pk=idz).update(file=url)



    course.objects.filter(pk=idz).update(course_name=coursename,discription=discription,duration=duration,amount=amount)
    return redirect("myapp:admin_view_course")







def admin_view_course(request):
    c = course.objects.all()
    return render(request,"admin/view_course.html", {'c' : c})

def admin_view_course_search(request):
    search=request.POST['textfield']
    c = course.objects.filter(course_name__icontains=search)
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

def admin_view_faculty_search(request):
    search=request.POST['textfield']
    c=faculty.objects.filter(facultyname__icontains=search)
    return render(request,"admin/view_faculty.html",{'c' : c})



def admin_view_faculty_edit(request,id):
    res=faculty.objects.get(pk=id)
    request.session['fid']=id
    c = batch.objects.all()

    return render(request, "admin/edit_faculty.html",{'c': res,'d': c})

def admin_faculty_update(request):
    fname=request.POST['textfield']
    gender=request.POST['radiobutton']
    email=request.POST['textfield2']
    phonnum=request.POST['textfield3']
    qualification = request.POST['qualification']
    experince = request.POST['experince']


    house=request.POST['textfield4']
    place=request.POST['textfield5']
    post=request.POST['textfield6']
    pin=request.POST['textfield7']
    # bname=request.POST['select']



    idz=request.session['fid']

    # qq=batch.objects.get(id=bname)
    obj=faculty.objects.get(pk=idz)
    obj.facultyname=fname
    obj.gender=gender
    obj.email=email
    obj.phone_no=phonnum
    obj.experience=experince
    obj.qualifiction=qualification
    if 'file' in request.FILES:
        image = request.FILES['file']
        fs = FileSystemStorage()
        sa = fs.save(image.name, image)
        url = fs.url(sa)
        obj.image=url
    obj.house_name=house
    obj.place=place
    obj.post=post
    obj.pin=pin
    # obj.BATCH_id=qq
    obj.save()
    c=faculty.objects.all()
    return  render(request,"admin/view_faculty.html",{'c': c})







def admin_view_faculty_del(request,id):
    res=faculty.objects.get(id=id)
    res.delete()
    c=faculty.objects.all()
    return render(request, "admin/view_faculty.html", {'c': c})





def admin_view_student(request):
    a=course.objects.all()
    c = student.objects.all()
    # d= course.objects.all()
    # e=batch.objects.all()
    return render(request,"admin/view_student.html",{'c': c,'a':a})

def admin_view_student_search(request):
    btn = request.POST['Submit']
    print(btn)
    if btn=="Search":
        a = course.objects.all()
        search = request.POST['textfield']
        c=student.objects.filter(student_name__icontains=search)
        return render(request,"admin/view_student.html",{'c' : c,'a':a})
    if btn=="Go":
        a = course.objects.all()
        cour = request.POST['select']
        print("courz")
        print(cour)
        bat = request.POST['select2']
        print("batch")
        print(bat)
        res1 = course.objects.get(pk=cour)

        res = batch.objects.get(id=bat,COURSE=res1.id)
        print(res)

        # res3 = batch.objects.get(pk=res.id)
        # print(res3)
        # cc = batch.objects.get(id__contains=res.id)

        c = student.objects.filter(BATCH_id=res)
        print("student")
        print(c)


        return render(request, "admin/view_student.html",{'c': c,'a':a})


def admin_view_student_del(request,id):
    print("jjj")
    res=student.objects.get(id=id)
    res.delete()
    print("jjjj222")
    # return HttpResponse("jjjj22")

    c = student.objects.all()
    return render(request, "admin/view_student.html", {'c': c})



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


def admin_view_salary_edit(request,id):

    res=salary.objects.get(pk=id)
    print(res)
    request.session['ssid']=id
    return render(request, "admin/edit_salary.html",{'c':res})


def admin_salary_update(request):

    salaryy=request.POST['textfield3']

    idz=request.session['ssid']
    qry=salary.objects.filter(pk=idz).update(salary=salaryy)
    return redirect("/stock/admin_view_salary/")






def admin_view_attendence(request):
    c=facultyattedence.objects.all()



    return render(request,"admin/view_attendence.html",{"c": c})


def admin_add_faculty_attendence(request):
    c = faculty.objects.all()
    return render(request,"admin/add_faculty_attendence.html",{"c": c})

def admin_add_faculty_attendence_post(request):
    check_values = request.POST.getlist('tag')
    print("hhh=",check_values)
    c = faculty.objects.all()

    for i in check_values:

        print(i)
        fa=facultyattedence()
        qq = faculty.objects.get(pk=i)
        ppp=datetime.datetime.now().date()
        ss=facultyattedence.objects.filter(FACULTY=qq,date=ppp)



        if ss.exists():
            print("nop")
        else:
            print("entr")
            print(qq)
            fa.FACULTY = qq
            fa.date = datetime.datetime.now().date()
            fa.save()

    return render(request, "admin/add_faculty_attendence.html", {"c": c})




def admin_view_attendence_search(request):
    year=request.POST['select1']
    month=request.POST['select2']


    c = facultyattedence.objects.filter( date__year=year,date__month=month)
    print(c)

    return render(request,"admin/view_attendence.html",{'c' : c})


def admin_view_achievement_search(request):
    search=request.POST['textfield']
    c = archiement.objects.filter(STUDENT__student_name__icontains=search)

    return render(request,"admin/view_archievment.html",{'c' : c})






#############################################


def ad_faculty_login(request):

    username = request.POST['uname']
    print(username)
    password = request.POST['pwd']
    print(password)
    login_obj22 = login.objects.get(username=username, password=password)

    print(login_obj22)

    print(login_obj22.user_type)
    ty=login_obj22.user_type

    login_obj=login.objects.filter(username=username, password=password)

    if login_obj.exists():
        print("ooooooooo")
        if ty =='student':
            print("mmmmmmmmm")

            login_obj = login.objects.get(username=username, password=password, user_type='student')

            lg = login_obj.pk
            typ = login_obj.user_type
            studenobj= student.objects.get(LOGIN=login_obj)


            return JsonResponse(
                {'status': 'ok', 'id': lg, 'usertype': typ,'batch_id': studenobj.BATCH.pk})



        if ty =='faculty':

            login_obj = login.objects.get(username=username, password=password, user_type='faculty')

            lg = login_obj.pk
            typ=login_obj.user_type
            print("llw")
            fac=faculty.objects.get(LOGIN=lg)

            print("fff")
            print(fac)

            fac_ass=batchassign.objects.filter(FACULTY=fac)
            bb=""
            bname=""
            cname=""

            for ii in fac_ass:
                print(ii.BATCH.id)

                bb=bb+str(ii.BATCH.id)+","
                bname=bname+ii.BATCH.batch_name+","
                cname=cname+ii.BATCH.COURSE.course_name+","

            print(bb)
            print(bname)
            print(cname)
            print("hhhh")



            return JsonResponse({'status': 'ok', 'id': lg,'usertype': typ,'batch_id':bb,'bname':bname,'cname':cname})
    else:
        return JsonResponse({'status': 'no'})



def ad_faculty_view_profile(request):
    lid = request.POST['lid']
    login_obj = login.objects.get(id=lid)
    user_obj=faculty.objects.get(LOGIN=login_obj)
    data = {"status": "ok", 'image':user_obj.image, 'facultyname':user_obj.facultyname, 'email':user_obj.email,'phonenumber':user_obj.phone_no,'house':user_obj.house_name,'place':user_obj.place,'experince':user_obj.experience,'qualification':user_obj.qualifiction,'post':user_obj.post,'pin':user_obj.pin}
    return JsonResponse(data)



def ad_view_courses(request):
    user_obj = course.objects.all()
    res2 = []
    for ii in user_obj:
        ss = {'Course_name': ii.course_name, 'description': ii.discription, 'duration': ii.duration, 'fees': ii.amount}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)


def ad_view_faculties(request):
    user_obj = faculty.objects.all()
    print(user_obj)
    res2 = []
    for ii in user_obj:
        ss = {'id':ii.pk, 'faculty_name': ii.facultyname, 'Email': ii.email, 'Phone': ii.phone_no, 'Image': ii.image,'housename':ii.house_name,'place':ii.place,'post':ii.post,'pin':ii.pin,'qualification':ii.qualifiction,'experince':ii.experience}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)

def ad_view_assigned_batches(request):
    print("ha")

    lid = request.POST['lid']
    print(lid)
    login_obj = login.objects.get(id=lid)
    fac_obj = faculty.objects.get(LOGIN=login_obj)
    #user_obj = batch.objects.get(id=fac_obj.BATCH_id)

    batchass = batchassign.objects.filter(FACULTY_id=fac_obj)
    print(batchass)

    res2 = []
    for ii in  batchass:
        ss = {'id': ii.BATCH.pk, 'Batch_name': ii.BATCH.batch_name, 'Batch_year': ii.BATCH.batch_year, 'batch_month':  ii.BATCH.batch_month, 'course_name':  ii.BATCH.COURSE.course_name}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)




# def ad_view_assigned_batches(request):
#     lid = request.POST['uid']
#
#     login_obj = login.objects.get(id=lid)
#     fac_obj = faculty.objects.get(LOGIN=login_obj)
#
#     # user_obj = batch.objects.get(id=fac_obj.BATCH_id)
#     batchass = batchassign.objects.all()
#     data = {"status": "ok", 'Batch_name': batchass.BATCH.batch_name, 'Batch_year': batchass.BATCH.batch_year, 'batch_month':  batchass.BATCH.batch_month, 'course_name':  batchass.BATCH.COURSE.course_name}
#     return JsonResponse(data)
#



def ad_view_students(request):
    print("ppp")

    bid= request.POST['bid']
    batch_obj=batch.objects.get(pk=bid)


    user_obj = student.objects.filter(BATCH=batch_obj)

    res2=[]
    for ii in user_obj:
        ss = {'id': ii.pk,'student_name': ii.student_name, 'email': ii.email, 'phone_no': ii.phone_no, 'course_name': ii.BATCH.COURSE.course_name}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)


def and_study_materials(request):
    lid=request.POST['lid']
    qq = faculty.objects.get(LOGIN_id=lid)
    user_obj = studymaterial.objects.filter(FACULTY_id=qq.id).order_by('-id')
    print(user_obj)
    res2 = []
    # qq=batch.objects.get(id=)
    for ii in user_obj:
        ss = {'date': ii.date, 'title': ii.title, 'file': ii.file,'id': ii.pk}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)

def and_add_study_material(request):
    print("jjj")

    title=request.POST["title"]
    ff=request.POST["file22"]
    lid=request.POST["lid"]

    batchs=request.POST["bid"]

    qq=faculty.objects.get(LOGIN_id=lid)
    rr=batch.objects.get(id=batchs)

    # courseid=qq.BATCH.COURSE_id

    print("nooo")

    import base64

    a = base64.b64decode(ff)
    # img
    fh = open("G:\\Project\\myapp\\media\\" + title + ".pdf", "wb")
    fh.write(a)
    fh.close()

    path="/media/"+title+".pdf"

    print("sss")
    # image = open("/media/image.pdf", "wb")
    # image.write(ff.decode('base64'))
    # image.close()

    # with open("/media/"+title+".pdf", "wb") as fh:
    #     fh.write(base64.urlsafe_b64decode(ff))

    print("qqq")
    ss=studymaterial()
    ss.title=title
    ss.file=path
    ss.COURSE_id=rr.COURSE.id

    ss.FACULTY_id=qq.id
    # ss.COURSE_id=
    ss.date = datetime.datetime.now().date()
    ss.save()


    data = {"status": "ok"}
    return JsonResponse(data)


def faculty_del_material(request):
    id= request.POST['id']
    studymaterialobj= studymaterial.objects.get(pk=id)
    studymaterialobj.delete()

    return JsonResponse({'status':'ok'})

def faculty_add_work(request):
    lid=request.POST['lid']
    batchid=request.POST['batchid']
    descr=request.POST['descr']
    fa=faculty.objects.get(LOGIN_id=lid)

    ww=work()
    ww.FACULTY=fa
    ww.BATCH_id=batchid
    ww.discrption=descr
    ww.date=datetime.datetime.now().date()
    ww.save()
    return JsonResponse({'status': 'ok'})

def faculty_del_work(request):
    wid=request.POST['wid']
    ww=work.objects.get(id=wid)
    ww.delete()
    return JsonResponse({'status': 'ok'})

def faculty_view_work(request):
    lid = request.POST['uid']
    fa = faculty.objects.get(LOGIN_id=lid)
    ww=work.objects.filter(FACULTY=fa)
    res2 = []
    for ii in ww:
        ss = {'course': ii.BATCH.COURSE.course_name, 'batch': ii.BATCH.batch_name, 'descr': ii.discrption,
              'id': ii.pk}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)

def faculty_update_work(request):
    wid=request.POST['wid']
    descr=request.POST['descr']
    ww=work.objects.get(id=wid)
    ww.discrption=descr
    ww.save()
    return JsonResponse({"status": "ok"})

def newtem(request):
    return render(request,"home_temp.html")



def and_add_syllbus(request):
    print("jjj")

    title=request.POST["title"]
    ff=request.POST["file22"]
    lid=request.POST["lid"]


    courseid=request.POST["cid"]

    courseobj= course.objects.get(pk=courseid)
    print("nooo")

    import base64
    a = base64.b64decode(ff)
    # img
    fh = open("G:\\Project\\myapp\\media\\" + title + ".pdf", "wb")
    fh.write(a)
    fh.close()
    path="/media/"+title+".pdf"
    print("sss")
    # image = open("/media/image.pdf", "wb")
    # image.write(ff.decode('base64'))
    # image.close()

    # with open("/media/"+title+".pdf", "wb") as fh:
    #     fh.write(base64.urlsafe_b64decode(ff))
    print("qqq")
    ss=syllabus()
    ss.title=title
    ss.file=path
    ss.COURSE_id=courseid
    ss.save()
    data = {"status": "ok"}
    return JsonResponse(data)



def stud_add_achievements(request):
    print("jjj")

    name=request.POST["name"]
    ff=request.POST["file"]
    lid=request.POST["lid"]
    description=request.POST["description"]

    ww=student.objects.get(LOGIN_id=lid)

    import base64
    a = base64.b64decode(ff)
    # img
    fh = open("G:\\Project\\myapp\\media\\" + name + ".pdf", "wb")
    fh.write(a)
    fh.close()
    path="/media/"+name+".pdf"
    print("sss")

    print("qqq")
    ss=archiement()
    ss.name=name
    ss.file=path
    ss.STUDENT=ww
    ss.discription=description
    ss.date=datetime.datetime.now()
    ss.save()
    data = {"status": "ok"}
    return JsonResponse(data)



def faculty_del_sylabus(request):
    wid=request.POST['wid']
    ww=syllabus.objects.get(id=wid)
    ww.delete()
    return JsonResponse({'status': 'ok'})

def faculty_update_sylabus(request):
    wid=request.POST['sid']
    title=request.POST['titl']
    ff = request.POST["file22"]
    ww=syllabus.objects.get(id=wid)
    ww.title=title
    if ff!="":
        import base64
        a = base64.b64decode(ff)
        # img
        fh = open("G:\\Project\\myapp\\media\\" + title + ".pdf", "wb")
        fh.write(a)
        fh.close()
        path = "/media/" + title + ".pdf"
        ww.file=path


    ww.save()
    return JsonResponse({"status": "ok"})


def faculty_view_sylabus(request):
    lid = request.POST['uid']
    fa = batchassign.objects.filter(FACULTY=faculty.objects.get(LOGIN=login.objects.get(pk=lid)))
    res2 = []
    for k in fa:

        ww=syllabus.objects.filter(COURSE=k.BATCH.COURSE)

        for ii in ww:
            ss = {'course': ii.COURSE.course_name, 'title': ii.title, 'file': ii.file,
                  'id': ii.pk}
            res2.append(ss)
        print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)


def and_edit_faculty_profile(request):

    fid=request.POST['fid']

    namee=request.POST['name']

    phonn=request.POST['phonenumber']
    housenamme=request.POST['housename']
    place=request.POST['place']
    post=request.POST['post']

    proo=request.POST['prof']

    obj = faculty.objects.get(LOGIN_id=fid)

    obj.facultyname = namee
    obj.phone_no = phonn
    obj.house_name = housenamme
    obj.place = place

    obj.post = post
    if proo != "":
        import base64
        # a = base64.b64decode(proo)

        imgdata = base64.b64decode(proo)
        import datetime

        abc = "media/" + str(fid) + ".jpg"
        print("jjjj22")

        filename = "G:\\Project\\myapp\\media\\" + str(fid) + ".jpg"
        fh = open(filename, "wb")

        path = "/media/" + str(fid) + ".jpg"

        fh.write(imgdata)
        fh.close()
        obj.image = path


        # with open(filename, 'wb') as f:
        # f.write(imgdata)


        # ww.image=path

    obj.save()
    return JsonResponse({"status": "ok"})


##########################################################################
def ad_student_view_profile(request):

    lid = request.POST['lid']

    login_obj=login.objects.get(id=lid)
    user_obj=student.objects.get(LOGIN=login_obj)


    # user_obj=student.objects.get(pk=lid)

    data = {"status": "ok", 'image':user_obj.image, 'name':user_obj.student_name, 'phonenumber':user_obj.phone_no,'email':user_obj.email,'house':user_obj.housename,'place':user_obj.place,'post':user_obj.post,'pin':user_obj.pin,'coursename':user_obj.BATCH.COURSE.course_name,'batchname':user_obj.BATCH.batch_name}
    return JsonResponse(data)



def and_edit_student_profile(request):

    sid=request.POST['sid']
    namee=request.POST['name']
    phonn=request.POST['phonenumber']
    housenamme=request.POST['housename']
    place=request.POST['place']
    proo=request.POST['prof']

    obj = student.objects.get(LOGIN_id=sid)

    obj.student_name = namee
    obj.phone_no = phonn
    obj.housename = housenamme
    obj.place = place

    # ww = student.objects.get(id=wid)
    #
    # ww.student_name = namee
    # ww.phone_no = phonn
    # ww.housename = housenamme
    # ww.place = place


    if proo != "":

        import base64
        # a = base64.b64decode(proo)

        imgdata = base64.b64decode(proo)
        import datetime



        abc = "media/" + str(sid) + ".jpg"
        print("jjjj22")

        filename = "G:\\Project\\myapp\\media\\" + str(sid) + ".jpg"
        fh = open(filename, "wb")


        path = "/media/" + str(sid) + ".jpg"

        fh.write(imgdata)
        fh.close()
        obj.image = path


        # with open(filename, 'wb') as f:
        # f.write(imgdata)


        # ww.image=path



    obj.save()
    return JsonResponse({"status": "ok"})


def and_student_view_stdy_materials(request):
    bid=str(request.POST['batchid']).replace(",","")
    # qq = student.objects.get(LOGIN_id=lid)
    bat=batch.objects.get(pk=bid)
    user_obj = studymaterial.objects.filter(COURSE=bat.COURSE)
    print(user_obj)
    res2 = []
    # qq=batch.objects.get(id=)
    for ii in user_obj:
        ss = {'date': ii.date, 'title': ii.title, 'file': ii.file,'coursename': ii.COURSE.course_name}
        # ss = {'date': ii.date, 'title': ii.title, 'file': ii.file,'id': ii.pk}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)



def student_view_sylabus(request):
    bid = str(request.POST['batchid']).replace(",", "")

    btch= batch.objects.get(pk=bid)
    courseobj= btch.COURSE


    #
    # fa = faculty.objects.get(LOGIN_id=lid)
    # ww=syllabus.objects.filter(COURSE=fa.BATCH.COURSE)

    user_obj = syllabus.objects.filter(COURSE=courseobj)

    res2 = []
    for ii in user_obj:
        ss = {'course': ii.COURSE.course_name, 'title': ii.title, 'file': ii.file,
              'id': ii.pk}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)




def student_view_work(request):
    bid = str(request.POST['batchid']).replace(",","")
    print(bid)
    btch = batch.objects.get(pk=bid)
    ww= work.objects.filter(BATCH=btch)
    res2 = []
    for ii in ww:
        ss = {'course': ii.BATCH.COURSE.course_name, 'batch': ii.BATCH.batch_name, 'descr': ii.discrption,
              'id': ii.pk}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)

def student_add_achivement(request):

    
    name = request.POST['textfield']
    gender = request.POST['radiobutton']
    email = request.POST['textfield2']
    phonenumber = request.POST['textfield3']
    qualification = request.POST['qualification']
    experince = request.POST['experince']

    image = request.FILES['file']
    fs = FileSystemStorage()
    sa = fs.save(image.name, image)
    url = fs.url(sa)
    print("hai")

    housename = request.POST['textfield4']
    place = request.POST['textfield5']
    post = request.POST['textfield6']
    pin = request.POST['textfield7']
    # batch_id=request.POST['select']
    password = random.randint(1000, 9999)
    ll = login()
    ll.username = email
    ll.password = str(password)
    ll.user_type = "faculty"
    ll.save()

    fa = faculty()
    fa.facultyname = name
    fa.gender = gender
    fa.email = email

    fa.phone_no = phonenumber
    fa.qualifiction = qualification
    fa.experience = experince
    fa.image = url
    fa.house_name = housename
    fa.place = place
    fa.post = post
    fa.pin = pin
    # print("ppppss=",batch_id)
    # qq=batch.objects.get(pk=batch_id)
    print("qsss")

    fa.LOGIN = ll
    print("qqq22")
    # fa.BATCH = qq
    fa.save()

    return render(request, "admin/add_faculty.html")





def studentviewstudents(request):

    batch_id=str(request.POST['batchid']) .replace(",","")

    allstudents=student.objects.filter(BATCH=batch.objects.get(pk=batch_id))

    res=[]

    if allstudents.exists():

        for i in allstudents:

            c={'id':i.pk,'student_name': i.student_name,'place': i.place,'pin': i.pin,'district':i.place,'contactno': i.phone_no, 'email':i.email,'photo':i.image,'gender':i.gender,'dob':i.dob }
            res.append(c)
        print(res,"kkkkkkkkkkkkkkkkkk")
        return JsonResponse({'status':'ok', 'users': res })
    else:
        return JsonResponse({'status':'no'})


def studentviewachievements(request):

    lid= request.POST['uid']

    allach=archiement.objects.filter(STUDENT__LOGIN_id=lid)

    res=[]

    if allach.exists():

        for i in allach:

            c={'id':i.pk,'date': i.date,'file': i.file,'name': i.name,'discription':i.discription,'status': i.status }
            res.append(c)

        return JsonResponse({'status':'ok', 'users': res })
    else:
        return JsonResponse({'status':'no'})











def fac_viewassignedcourse(request):
    flid= request.POST['uid']

    print(flid,"aaaaaaaaaaaaaaaaaaaaaaaa")
    facobj= faculty.objects.get(LOGIN= login.objects.get(pk=flid))
    batchass= batchassign.objects.filter(FACULTY=facobj)
    res=[]
    if batchass.exists():
        for i in batchass:
            c={'cid': i.BATCH.COURSE.pk, 'cname': i.BATCH.COURSE.course_name}
            res.append(c)
        return JsonResponse({'status':'ok','users': res})
    else:
        return JsonResponse({'status':'no'})

def fac_viewassignedbatches(request):
    flid = request.POST['uid']
    facobj = faculty.objects.get(LOGIN=login.objects.get(pk=flid))
    batchass = batchassign.objects.filter(FACULTY=facobj)
    res = []
    if batchass.exists():
        for i in batchass:
            c = {'bid': i.BATCH.pk, 'bname': i.BATCH.batch_name}
            res.append(c)
        return JsonResponse({'status': 'ok', 'users': res})
    else:
        return JsonResponse({'status': 'no'})






def publicviewcourse(request):


    batchass = course.objects.all()
    res = []
    if batchass.exists():
        for i in batchass:
            c = {'course_name':i.course_name, 'discription':i.discription, 'duration':i.duration, 'amount':i.amount,'content':i.file}
            res.append(c)
        return JsonResponse({'status': 'ok', 'users': res})
    else:
        return JsonResponse({'status': 'no'})



def publicqnquiryadd(request):

    name=request.POST["name"]
    email=request.POST["email"]
    phone=request.POST["phone"]
    senquiry=request.POST["enquiry"]

    enquiryobj=enquiry()
    enquiryobj.name=name
    enquiryobj.email=email
    enquiryobj.phone_no=phone
    enquiryobj.discrption=senquiry
    enquiryobj.date=datetime.datetime.now()
    enquiryobj.save()


    return JsonResponse({'status': 'ok'})





def student_view_faculties(request):
    batch_id=request.POST["batch_id"]
    batch_id=batch_id.replace(",","")

    batchassignall= batchassign.objects.filter(BATCH=batch.objects.get(pk=batch_id))

    res2 = []
    for ii in batchassignall:
        ss = {'id': ii.FACULTY.pk,'faculty_name': ii.FACULTY.facultyname, 'Email': ii.FACULTY.email, 'Phone': ii.FACULTY.phone_no, 'Image': ii.FACULTY.image,'housename':ii.FACULTY.house_name,'place':ii.FACULTY.place,'post':ii.FACULTY.post,'pin':ii.FACULTY.pin,'qualification':ii.FACULTY.qualifiction,'experince':ii.FACULTY.experience}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)


def studentviewcarriers(request):

    allcarrier= career.objects.all()
    res=[]

    if allcarrier.exists():
        for i in allcarrier:
            c={'date':i.date,'title':i.title,'discription':i.discription,'file':i.file,'lastdate':i.lastdate,'id': i.pk }
            res.append(c)

        return JsonResponse({'status':'ok','data': res})
    else:
        return JsonResponse({'status':'ok'})


def applyforvaccancy(request):
    sid= request.POST["sid"]
    cid= request.POST["cid"]


    studentobj= student.objects.get(LOGIN=login.objects.get(pk=sid))
    careerobj= career.objects.get(pk=cid)

    applicationobj=application()
    applicationobj.STUDENT=studentobj
    applicationobj.CAREER=careerobj
    applicationobj.date=datetime.datetime.now()
    applicationobj.save()

    return JsonResponse({'status': 'ok'})



def studentviewappliedlist(request):

    sid= request.POST["uid"]

    studentobj= student.objects.get(LOGIN=login.objects.get(pk=sid))



    allcarrier= application.objects.filter(STUDENT=studentobj)
    res=[]

    if allcarrier.exists():
        for i in allcarrier:
            c={'date':i.CAREER.date,'title':i.CAREER.title,'discription':i.CAREER.discription,'file':i.CAREER.file,'lastdate':i.CAREER.lastdate,'id': i.pk }
            res.append(c)

        return JsonResponse({'status':'ok','data': res})
    else:
        return JsonResponse({'status':'ok'})


def and_studentchatinsert(request):
    sid= request.POST["sid"]
    fid= request.POST["fid"]


    print("fid", fid)


    studentobj= student.objects.get(LOGIN=login.objects.get(pk=sid))
    facultyobj= faculty.objects.get(pk=fid)
    msg= request.POST["message"]
    chatobj= chat()
    chatobj.STUDENT=studentobj
    chatobj.FACULTY=facultyobj
    chatobj.message=msg
    chatobj.type="student"
    chatobj.date=datetime.datetime.now()
    chatobj.save()
    return JsonResponse({'status': 'ok'})


def and_facchatinsert(request):
    sid= request.POST["sid"]
    flogid= request.POST["flogid"]
    studentobj= student.objects.get(pk=sid)
    facultyobj= faculty.objects.get(LOGIN=login.objects.get(pk=flogid))
    msg= request.POST["message"]
    chatobj= chat()
    chatobj.STUDENT=studentobj
    chatobj.FACULTY=facultyobj
    chatobj.message=msg
    chatobj.type="faculty"
    chatobj.date=datetime.datetime.now()
    chatobj.save()
    return JsonResponse({'status': 'ok'})

def studentviewchat(request):
    sid = request.POST["sid"]
    fid = request.POST["facid"]
    lid = request.POST["lid"]
    studentobj = student.objects.get(LOGIN= login.objects.get(pk=sid))
    facultyobj = faculty.objects.get(pk=fid)

    chatall=chat.objects.filter(STUDENT=studentobj,FACULTY=facultyobj)
    res=[]

    if chatall.exists():
        for i in chatall:
            if i.pk > int(lid):
                c={'message': i.message ,'date': i.date ,'type': i.type,'id':i.pk}
                res.append(c)
        return JsonResponse({'status':'ok','res2': res})
    else:
        return JsonResponse({'status':'no'})


def facviewchat(request):
    lid = request.POST["lid"]

    sid = request.POST["sid"]
    flogid = request.POST["flogid"]
    studentobj = student.objects.get(pk=sid)
    facultyobj = faculty.objects.get(LOGIN=login.objects.get(pk=flogid))

    chatall=chat.objects.filter(STUDENT=studentobj,FACULTY=facultyobj)
    res=[]

    if chatall.exists():
        for i in chatall:
            if i.pk > int(lid):
                c={'message': i.message ,'date': i.date ,'type': i.type,'id':i.pk}
                res.append(c)
        return JsonResponse({'status':'ok','res2': res})
    else:
        return JsonResponse({'status':'no'})





def fac_attendanceadd(request):
    ids= request.POST["studid"]
    bid= request.POST["bid"]
    idss= ids.split(',')



    batchobj= batch.objects.get(pk=bid)

    studentall= student.objects.filter(BATCH=batchobj)

    absstudent=[]

    for o in studentall:

        if str(o.pk) not in idss:
            absstudent.append(str(o.pk))





    for i in idss:

        if len(i)> 0:
            dates= datetime.datetime.now()
            sobj= student.objects.get(pk=i)

            attadd= attendence()
            attadd.STUDENT=sobj
            attadd.date=dates
            attadd.status="Present"


            sa= attendence.objects.filter(STUDENT=sobj,date=dates)
            if sa.exists():
                pass
            else:

                attadd.save()

    for i in absstudent:
        dates = datetime.datetime.now()
        sobj = student.objects.get(pk=i)
        attadd = attendence()
        attadd.STUDENT = sobj
        attadd.date = dates
        attadd.status = "Absent"

        sa = attendence.objects.filter(STUDENT=sobj, date=dates)
        if sa.exists():
            pass
        else:

            attadd.save()

        # attadd.save()

    return JsonResponse({'status': 'ok'})




def facviewstudentattendance(request):

    sid= request.POST["sid"]

    allattendance= attendence.objects.filter(STUDENT= student.objects.get(pk=sid))

    res=[]
    if allattendance.exists():

        for i in allattendance:

            c={'date': i.date ,'status': i.status}
            res.append(c)
        return JsonResponse({'status':'ok', 'users': res})
    else:
        return JsonResponse({'status':'no'})









def studentattendance(request):

    sid= request.POST["sid"]

    allattendance= attendence.objects.filter(STUDENT= student.objects.get(LOGIN=login.objects.get(pk=sid)))

    res=[]
    if allattendance.exists():

        for i in allattendance:

            c={'date': i.date ,'status': i.status}
            res.append(c)
        return JsonResponse({'status':'ok', 'users': res})
    else:
        return JsonResponse({'status':'no'})




def publicviewachievements(request):

    alls= archiement.objects.all() #.filter(status="Approved")

    res=[]
    if alls.exists():
        for i in alls:
            c={'id': i.pk,'date':i.date,'file':i.file, 'description':i.discription,'name': i.STUDENT.student_name, 'image':i.STUDENT.image , 'email': i.STUDENT.email }
            res.append(c)
        return JsonResponse({'status': 'ok', 'data': res })
    else:
        return JsonResponse({'status':'no'})






def senreviews(request):
    sid= request.POST["sid"]
    rev= request.POST["review"]
    studentobj= student.objects.get(LOGIN=login.objects.get(pk=sid))

    reviewobj= review()
    reviewobj.STUDENT= studentobj
    reviewobj.COURSE=studentobj.BATCH.COURSE
    reviewobj.review=rev
    reviewobj.date=datetime.datetime.now()
    reviewobj.save()

    return JsonResponse({'status':'ok'})





def facviewsalary(request):

    fid= request.POST["userid"]

    print(fid,"aaaaaaaaaaaaaaaaaaaaaaaaaaa")

    salrys= salary.objects.filter(FACULTY= faculty.objects.get(LOGIN= login.objects.get(pk=fid)))

    res=[]

    if salrys.exists():
        k=[]

        for i in salrys:
            c={'year': i.year,'month':i.month, 'salary': i.salary }
            res.append(c)


        print(res)
        return JsonResponse({'status':'ok', 'res': res})

    else:

        return JsonResponse({'status':'no'})



















