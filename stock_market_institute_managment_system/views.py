from django.shortcuts import render
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse, request, JsonResponse
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
    c = career.objects.filter(title__contains=search)
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
        fa.image=url
        fa.house_name=housename
        fa.place=place
        fa.post=post
        fa.pin=pin
        print("ppppss=",batch_id)
        qq=batch.objects.get(pk=batch_id)
        print("qsss")

        fa.LOGIN=ll
        print("qqq22")


        fa.BATCH = qq
        fa.save()

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
    image = request.FILES['file']
    housename = request.POST['textfield4']
    place = request.POST['textfield5']
    post = request.POST['textfield6']
    pin = request.POST['textfield7']
    batch = request.POST['select']

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
        id=request.session['fac_id']
        res = faculty.objects.get(pk=id)
        ss=salary.objects.filter(FACULTY=res)
        if ss.exists():
            ss=ss[0]
            ss.salary=sal
            ss.save()
        else:
            s=salary()
            s.salary=sal
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
    qry=course.objects.filter(pk=idz).update(course_name=coursename,discription=discription,duration=duration,amount=amount)
    return redirect("myapp:admin_view_course")







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

def admin_view_faculty_search(request):
    search=request.POST['textfield']
    c=faculty.objects.filter(facultyname__contains=search)
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


    house=request.POST['textfield4']
    place=request.POST['textfield5']
    post=request.POST['textfield6']
    pin=request.POST['textfield7']
    bname=request.POST['select']



    idz=request.session['fid']

    qq=batch.objects.get(id=bname)
    obj=faculty.objects.get(pk=idz)
    obj.facultyname=fname
    obj.gender=gender
    obj.email=email
    obj.phone_no=phonnum
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
    obj.BATCH_id=qq
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
        c=student.objects.filter(student_name__contains=search)
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
    search=request.POST['d']
    c = facultyattedence.objects.filter(date__contains=search)
    return render(request,"admin/view_attendence.html",{'c' : c})



#############################################


def ad_faculty_login(request):

    username = request.POST['uname']
    print(username)
    password = request.POST['pwd']
    print(password)
    login_obj = login.objects.filter(username=username, password=password,user_type='faculty')
    print(login_obj)
    if login_obj.exists():
        login_obj = login.objects.get(username=username, password=password, user_type='faculty')

        lg = login_obj.pk
        typ=login_obj.user_type
        print("llw")
        return JsonResponse({'status': 'ok', 'id': lg,'usertype': typ})
    else:
        return JsonResponse({'status': 'no'})



def ad_faculty_view_profile(request):
    lid = request.POST['lid']
    login_obj = login.objects.get(id=lid)
    user_obj=faculty.objects.get(LOGIN=login_obj)
    data = {"status": "ok", 'image':user_obj.image, 'facultyname':user_obj.facultyname, 'email':user_obj.email,'phonenumber':user_obj.phone_no,'house':user_obj.house_name,'place':user_obj.place,'post':user_obj.post,'pin':user_obj.pin}
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
        ss = {'faculty_name': ii.facultyname, 'Email': ii.email, 'Phone': ii.phone_no, 'Image': ii.image,'housename':ii.house_name,'place':ii.place,'post':ii.post,'pin':ii.pin}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)





def ad_view_assigned_batches(request):
    lid = request.POST['uid']

    login_obj = login.objects.get(id=lid)
    fac_obj = faculty.objects.get(LOGIN=login_obj)

    user_obj = batch.objects.get(id=fac_obj.BATCH_id)

    data = {"status": "ok", 'Batch_name': user_obj.batch_name, 'Batch_year': user_obj.batch_year, 'batch_month': user_obj.batch_month, 'course_name': user_obj.COURSE.course_name}
    return JsonResponse(data)


def ad_view_students(request):
    print("ppp")

    lid=request.POST['uid']
    login_id=login.objects.get(id=lid)

    fac_id=faculty.objects.get(LOGIN=login_id)
    print(fac_id.BATCH.pk)
    print("hi")
    batch_obj=batch.objects.get(pk=fac_id.BATCH.pk)
    print("vvvv")




    user_obj = student.objects.filter(BATCH=batch_obj)
    res2 = []

    for ii in user_obj:
        ss = {'student_name': ii.student_name, 'email': ii.email, 'phone_no': ii.phone_no, 'course_name': ii.BATCH.COURSE.course_name}
        res2.append(ss)
    print(res2)
    data = {"status": "ok", "users": res2}
    return JsonResponse(data)


