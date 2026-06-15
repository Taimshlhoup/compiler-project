count = 10
count = "high"
is_student = True
name = "Khaled"
total = count + offset
result = count * 2
final = result / 5

print(x)
score = 20
if score > 50 :
    print("Pass")
y = 10
y = "khaled"
z = "hello" + 5
s = "a" - "b"
k = 5 + 3
str = "Ali" + "Ahmad"
p = "1" / "3"
o = "2" * "4"
ct = "a"
a_b = 9
app = ct * 2
unknown_variable = "hhhhvh"

def index():

    return render_template('Jinja-test.j2', unknown_variable=unknown_variable )

# --- 1. حالات الـ Type Error (عمليات ممنوعة على أنواع متطابقة) ---
# نصوص مع نصوص
s1 = "Ahmad" - "Ali"     # خطأ: طرح النصوص
s2 = "Khaled" / "User"    # خطأ: قسمة النصوص
s3 = "Hello" * "World"    # خطأ: ضرب نص في نص

# قيم منطقية (Boolean) مع قيم منطقية
b1 = True + False         # خطأ: جمع قيم منطقية
b2 = True - True          # خطأ: طرح قيم منطقية
b3 = False * True         # خطأ: ضرب قيم منطقية
b4 = True / False         # خطأ: قسمة قيم منطقية


# --- 2. حالات الـ Type Mismatch (عمليات بين أنواع مختلفة تماماً) ---
m1 = "hello" + 5          # خطأ: جمع نص مع رقم صحيح
m2 = 10 - "test"          # خطأ: طرح نص من رقم صحيح
m3 = True + 3             # خطأ: جمع قيمة منطقية مع رقم صحيح
m4 = 5.5 * "text"         # خطأ: ضرب رقم عشري في نص
m5 = "hi" / 2.5           # خطأ: قسمة نص على رقم عشري


# --- 3. حالات سليمة (Type Promotion / Valid Operations) ---
# يجب ألا تطبع أي خطأ حسابي في الكونسول
valid_1 = 5 + 3           # سليم: Integer + Integer
valid_2 = 4.5 + 2         # سليم: Float + Integer (يحدث ترقية للنوع)
valid_3 = "Pure " + "Text" # سليم: الحاق النصوص (String Concatenation)

for i in range(5)
    inside_var = 10
    print(i)

print(i)
print(inside_var)

for j in range(0)
    qwe = 5

print(qwe)



