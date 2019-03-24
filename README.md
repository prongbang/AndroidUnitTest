# Android Unit Test

1. ใช้ @Mock เพื่อจำลองพฤติกรรมของวัตถุนั้นทั้งกระบวนการ ด้วยคำสั่ง when()

2. ใช้ @Spy เพื่อให้วัตถุนั้นทำงานจริงแต่เปลี่ยนพฤติกรรมบางเม็ทเถิด (method) ได้

3. ใช้ @Captor เพื่อเก็บข้อมูลจากการประมวลผลเอาไปเปรียบเทียบกับค่าที่เราต้องการได้

4. ใช้ verify() เพื่อพิสูจน์ว่ามีการเรียกใช้เม็ทเถิดนั้นๆหรือไม่

5. ถ้าจะใช้แอนโนเทชั่น (Annotation) เหล่านี้ จะต้องแอนโนเถต (Annotate) เจยูนิตคลาสด้วย @RunWith(MockitoJUnitRunner.class)

## Run test

```bash
./gradlew test
```

- Test report

```
AndroidUnitTest/app/build/reports/tests/testDebugUnitTest/index.html
```