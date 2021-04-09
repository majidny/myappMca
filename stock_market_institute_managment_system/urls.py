from django.urls import path
from . import views
from  django.conf import settings
from django.conf.urls.static import static
app_name="myapp"
urlpatterns=[
    path('',views.logins),



    path('admin_hom/',views.admin_home),
    path('abc/',views.abc),


    path('admin_add_batch_post/', views.admin_add_batch_post),

    path('admin_edit_batch/',views.admin_edit_batch),
    path('admin_edit_batch_post/', views.admin_edit_batch_post),


    path('admin_add_career_post/', views.admin_add_career_post),

    path('admin_edit_career/',views.admin_edit_career),
    path('admin_edit_career_post/', views.admin_edit_career_post),


    path('admin_add_course_post/',views.admin_add_course_post),

    # path('admin_edit_course/',views.admin_edit_course),
    # path('admin_edit_course_post/', views.admin_edit_course_post),


    path('admin_add_faculty_post/', views.admin_add_faculty_post),

    path('admin_edit_faculty/',views.admin_edit_faculty),
   # path('admin_edit_faculty_post/', views.admin_edit_faculty_post),
    path('admin_view_faculty_edit/<str:id>',views.admin_view_faculty_edit),
    path('admin_faculty_update/',views.admin_faculty_update),


    path('admin_add_faculty_attendence/',views.admin_add_faculty_attendence),

    path('admin_add_salary/<str:id>',views.admin_add_salary),
    path('admin_add_salary_post/', views.admin_add_salary_post),

    path('admin_edit_salary/',views.admin_edit_salary),
    path('admin_edit_salary_post/', views.admin_edit_salary_post),


    path('admin_add_student_post/', views.admin_add_student_post),

    path('admin_edit_student/',views.admin_edit_student),
    path('admin_edit_student_post/', views.admin_edit_student_post),



    path('admin_view_archievment/',views.admin_view_archievment),
    path('admin_view_attendence/',views.admin_view_attendence),

    path('admin_view_batch/',views.admin_view_batch),
    path('admin_view_batch_search/',views.admin_view_batch_search),
    path('admin_view_batch_edit/<str:id>/<str:bname>',views.admin_view_batch_edit),
    path('admin_view_batch_del/<str:id>',views.admin_view_batch_del),
    path('admin_batch_update/',views.admin_batch_update),

    path('admin_view_career/',views.admin_view_career),
    path('admin_view_career_applicant/',views.admin_view_career_applicant),

    path('admin_view_course/',views.admin_view_course,name="admin_view_course"),
    path('admin_view_course_search/',views.admin_view_course_search),

    path('admin_view_course_edit/<str:id>',views.admin_view_course_edit),

    path('admin_view_course_del/<str:id>', views.admin_view_course_del),
    path('admin_course_update/',views.admin_course_update),

    path('admin_view_enquery/', views.admin_view_enquery),

    path('admin_view_faculty/', views.admin_view_faculty),
    path('admin_view_faculty_del/<str:id>', views.admin_view_faculty_del),

    path('admin_view_review/', views.admin_view_review),
    path('admin_view_salary/', views.admin_view_salary),
    path('admin_view_student/', views.admin_view_student),


]


# if settings.DEBUG:
#
#     urlpatterns += static(settings.MEDIA_URL,document_root=settings.MEDIA_ROOT)
