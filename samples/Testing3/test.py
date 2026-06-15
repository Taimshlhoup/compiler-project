from flask import Flask

app = Flask(__name__)

# بيانات تجريبية
user_role = "Admin"
login_attempts = 3
is_active = True

# 1. اختبار الـ Type Mismatch في العمليات الحسابية
# المترجم يجب أن يكتشف أنك تحاول جمع نص مع رقم
total_score = user_role + login_attempts

# 2. اختبار الـ Undefined Variables
# المتغير 'config_value' لم يتم تعريفه
app_priority = config_value * 2

# 3. اختبار الـ For Loop (حسب القواعد الحالية في الـ Parser الخاص بك)
# بما أن القاعدة تدعم: FOR atom IN python_expr statement
for x in user_role print(x)

# 4. اختبار منطق الـ Comparison (المقارنة)
# هل ينجح المترجم في مقارنة نص مع نص؟
if user_role == "Guest":
    status = "Limited"
else:
    status = "Full Access"

# 5. خطأ نوع متعمد في الإسناد (Assignment Type Check)
# تحويل 'is_active' من Boolean إلى Integer (إذا كان جدولك يمنع تغيير النوع)
is_active = 100