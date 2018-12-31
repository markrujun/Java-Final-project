# Java-Final-project

> 马如骏
>
> 161278019

## 项目简介

- 葫芦娃大战，在选择界面选择带你出征的葫芦娃去消灭妖精吧！
- 键盘`↑` `↓`  `←` `→` 操控葫芦娃上下左右行走，键盘`x` 按下松开后葫芦娃进行攻击。（可以组合按键斜着走哦！）

## 精彩回放

葫芦娃胜利

![](https://github.com/markrujun/Java-Final-project/blob/master/screenShot/win.gif?raw=true)

葫芦娃失败

![](https://github.com/markrujun/Java-Final-project/blob/master/screenShot/lose.gif?raw=true)



## 游戏说明

#### 角色介绍

| 名字 | 血量 | 攻击力 | 攻击范围 | 介绍                                     |
| ---- | ---- | ------ | -------- | ---------------------------------------- |
| 大娃 | 3000 | 500    | 500      | 大娃力大无穷，血多，手短，攻击力较低     |
| 二娃 | 1500 | 700    | 1000     | 二娃千里眼，血少，手长，攻击力高         |
| 三娃 | 4000 | 200    | 300      | 三娃金刚不坏，血多，手段，攻击力低，肉盾 |
| 四娃 | 2000 | 400    | 700      | 四娃吐火，属性中庸                       |
| 五娃 | 2000 | 400    | 700      | 五娃喷水，属性中庸                       |
| 六娃 | 2500 | 300    | 700      | 六娃（隐身？不会）                       |
| 七娃 | 2500 | 300    | 700      | 七娃（葫芦？没有）                       |
| 喽啰 | 1000 | 200    | 700      | 小怪，很垃圾                             |
| 蝎精 | 2000 | 500    | 700      | 精英怪，有点厉害                         |

1. 关于`棋盘`，由于参考了老师在群里发的一个游戏视频，想实现一个能够玩家控制的游戏，但因为`棋盘`这一概念，与葫芦娃的操作有一些冲突，就没有实现`棋盘`。
2. 关于`复盘`，由于操作过程中，任务频繁移动，不太容易实现复盘，能力有限就没有实现。但实现了战斗的记录，将战斗过程一些事件记录在文件中（有文件的简单读写）。

## 项目实现

#### 项目结构

```
java
│
└───Annotation 注释
│___Config 全局配置，如屏幕大小，战场边界
│___gui  图形界面Controller
|___logic
|	|____battle 战场
│	|____Bullet 子弹
|	|____creatrue 生物
|	
└───util 日志记录
```

### 1. Creature UML图

![1546232913107](https://github.com/markrujun/Java-Final-project/blob/master/screenShot/creatre_UML.png?raw=true)

### 2. 子弹UML图

![1546233021534](https://github.com/markrujun/Java-Final-project/blob/master/screenShot/bullet_UML.png?raw=true)

### 3. 游戏模型

- 在用户选择完葫芦娃之后，便会根据用户选择的葫芦娃生成一个**单例** `BattleClient`,

  ```java
  private BattleClient(int CalabashIndex, Pane battlePane) {
          this.battlePane = battlePane;
          switch (CalabashIndex){
              case 0:
                  player = RedBrother.getInstance();
                  break;
             .....
          }
      }
  ```

  之后就执行三个初始化：

  ```java
  public void init() {
          System.out.println("begin init");
          playerInit(); // 葫芦娃初始化
          evilLeagueInit(); // 怪物初始化
          threadBegin(); // 线程开启
      }
  ```

  1. 葫芦娃初始化包括，添加键盘监听事件，葫芦娃初始站位
  2. 怪物初始化包括，怪物的生成，邪恶阵容的控制开启
  3. 线程开启包括， 主要实现UI界面的刷新，因为在程序内部，是无法给外部的Pane画布添加子节点的，因此，如果需要在内部添加一个子弹，或者添加一个怪物，需要一个线程，持续的监听是否有新的节点需要添加。

### 4. 多线程

程序中包含了多个线程，其结构如下

```
BattleClientTread-----------------------检查游戏是否结束（葫芦娃阵亡或怪物全军覆没）
|______PlayTread------------------------检查葫芦娃是否死亡和葫芦娃是否被攻击
|	   |______ButtelTread---------------检查子弹是否超过其攻击范围和子弹是否打到目标
|______EvilLeaguageTread----------------检查当前怪物是否死完（出下一波怪物）以及控制怪物随机行走攻击
|	   |______evilCreatureTread---------检查怪物是否死亡或被攻击
|	   |	  |______ButtelTread
```

 

### 5. UI界面

使用了JavaFX，主要是图片的刷新渲染从而实现动起来的效果。

## 课程要求

### 1. 面向对象

**继承**：从`create`类的继承，出现了`葫芦娃`和`怪物`两种**抽象**类，之后的从大娃到七娃，再到怪物中的喽啰和蝎子精，都是对create的继承

**封装**: `battleClient`就是对葫芦娃，邪恶阵容的封装使用，把这两个不同的类，封装在一起，才产生了这个精彩的战斗。

### 2. 异常处理、注释、输入输出

**异常处理**：用了异常处理，在程序发生错误，例如文件读取失败、线程被中断，等异常时，抛出异常

**注释**： 写了两种注释：`Author`注释和`description`注释，`Author`是作者信息的描述，`description`是对方法作用的注释

**文件处理**： 将战斗进程写在日志文件中

### 3. Maven自动化构建项目

使用maven，来自动下载依赖包，以及运行测试类

### 4. 多线程

在上面已经提及，需要补充的是，为了保障线程的安全，在需要时应该给线程加锁