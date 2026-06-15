# Fractional Knapsack – Greedy Algorithm

## What problem does it solve?

You have a knapsack with a **weight capacity `m`** and `n` items, each with a **weight** and **profit**. You want to **maximize profit** by selecting items (or fractions of items) to fill the knapsack.

---

## Core Idea – Greedy Strategy

> Always pick the item with the **highest profit-to-weight ratio** (p/w) first.

Since fractions are allowed, if an item doesn't fully fit, you take whatever fraction fits.

---

## Code Walkthrough

### Static Variables
```java
static int n;           // number of items
static float m;         // knapsack capacity
static float p[], w[];  // profit and weight arrays
```

---

### `greedy()` Method – Step by Step

```java
float u = m;   // u = remaining capacity (starts at full capacity)
float profit = 0;
```

**The outer loop** runs `n` times (once per item slot):
```java
for (i = 0; i < n; i++) {
```

**Step 1 – Find best item** (highest p/w ratio):
```java
for (int j = 0; j < n; j++) {
    if ((p[j] / w[j]) > max) {
        k = j;         // k = index of best item
        max = p[j]/w[j];
    }
}
```

**Step 2 – Can it fully fit?**
```java
if (w[k] > u) {
    // Take only a fraction
    profit += p[k] * (u / w[k]);
    break;  // knapsack is full, stop
}
else {
    // Take the whole item
    profit += p[k];
    u = u - w[k];   // reduce remaining capacity
    p[k] = 0;       // mark item as used (ratio becomes 0)
}
```
Setting `p[k] = 0` is the trick to **avoid picking the same item twice** in the next iteration.

---

## Dry Run Example

| Item | Weight | Profit | P/W Ratio |
|------|--------|--------|-----------|
| 1    | 10     | 60     | **6.0** ✅ |
| 2    | 20     | 100    | 5.0       |
| 3    | 30     | 120    | 4.0       |

Capacity = **50**

| Round | Item Picked | Action | Remaining Capacity | Profit |
|-------|-------------|--------|--------------------|--------|
| 1     | Item 1      | Full   | 40                 | 60     |
| 2     | Item 2      | Full   | 20                 | 160    |
| 3     | Item 3      | 20/30 fraction | 0          | 160 + 80 = **240** |

---

## Time Complexity

| Part | Complexity |
|------|-----------|
| Outer loop | O(n) |
| Inner loop (find max) | O(n) |
| **Total** | **O(n²)** |

> A smarter approach sorts by ratio first → O(n log n), but this code skips sorting and finds the max each time instead.

---

## Key Points to Remember for Your Exam

1. **Greedy choice** = always pick highest **profit/weight ratio**
2. **Fractional** knapsack (not 0/1) — fractions are allowed
3. `p[k] = 0` marks an item as "used" to prevent reuse
4. Loop breaks early once the knapsack is **exactly full**
5. Works correctly but runs in **O(n²)** due to repeated max-finding




# Dijkstra's Shortest Path Algorithm

## What problem does it solve?

Given a **weighted graph of cities**, find the **shortest path (minimum travel time)** from a **source city** to **all other cities**.

---

## Core Idea – Greedy Strategy

> Always extend the path through the **nearest unvisited city** first.

At every step, pick the unvisited city with the smallest known distance, then use it to update its neighbours.

---

## Key Data Structures

```java
int[] dist[];      // shortest known distance from source to each city
int[] tvertex[];   // visited set: tvertex[i]=1 means city i is finalized
int[][] cost[][];  // adjacency matrix: cost[i][j] = travel time between i & j
```

> `0` in the input matrix means **no direct road** → stored as `Integer.MAX_VALUE` (infinity)

---

## Code Walkthrough

### Initialization
```java
System.arraycopy(cost[source], 0, dist, 0, n);
// dist[] = direct distances from source (the source row of cost matrix)

tvertex[source] = 1;   // mark source as visited
dist[source] = 0;      // distance to itself is 0
```

---

### Main Loop — runs `n-1` times

#### Step 1 – Pick nearest unvisited city
```java
min = Integer.MAX_VALUE;
for (j = 0; j < n; j++) {
    if (tvertex[j] == 0 && dist[j] < min) {
        min = dist[j];
        u = j;          // u = nearest unvisited city
    }
}
tvertex[u] = 1;        // mark it as visited/finalized
```

#### Step 2 – Relax (update) neighbours of `u`
```java
for (v = 0; v < n; v++) {
    if (tvertex[v] == 0 &&              // v not yet finalized
        cost[u][v] != Integer.MAX_VALUE) // direct road exists
    {
        dist[v] = min(dist[v], dist[u] + cost[u][v]);
        //         old distance   vs   go through u
    }
}
```
This is called **edge relaxation** — the heart of Dijkstra's.

---

## Dry Run Example

4 cities, source = **0**

**Cost Matrix** (0 = no road = ∞):
```
     0    1    2    3
0  [ ∞    2    ∞    8 ]
1  [ 2    ∞    5    ∞ ]
2  [ ∞    5    ∞    1 ]
3  [ 8    ∞    1    ∞ ]
```

**Initial dist[ ]** = `[0, 2, ∞, 8]` (source row, dist[0]=0)

| Round | Visited `u` | dist[ ] after relaxation | tvertex |
|-------|-------------|--------------------------|---------|
| Start | 0           | [0, 2, ∞, 8]             | {0}     |
| 1     | 1 (min=2)   | [0, 2, **7**, 8]         | {0,1}   |
| 2     | 2 (min=7)   | [0, 2, 7, **8**]         | {0,1,2} |
| 3     | 3 (min=8)   | [0, 2, 7, 8]             | {0,1,2,3} |

**Output:**
```
from 0 to 0 → 0
from 0 to 1 → 2
from 0 to 2 → 7
from 0 to 3 → 8
```

---

## Time Complexity

| Part | Complexity |
|------|-----------|
| Outer loop | O(n) |
| Find min inner loop | O(n) |
| Relaxation inner loop | O(n) |
| **Total** | **O(n²)** |

---

## Key Points to Remember for Your Exam

| # | Point |
|---|-------|
| 1 | `tvertex[i] = 1` means city `i` is **permanently finalized** |
| 2 | `Integer.MAX_VALUE` represents **no direct road (∞)** |
| 3 | `dist[v] = min(dist[v], dist[u] + cost[u][v])` is **edge relaxation** |
| 4 | Greedy — always picks the **minimum distance unvisited** city next |
| 5 | Works only with **non-negative weights** |
| 6 | Gives shortest path from **one source to ALL vertices** |
| 7 | `System.arraycopy` seeds `dist[]` with the source row of cost matrix |




# Floyd-Warshall Algorithm

## What problem does it solve?

Find the **shortest path between ALL pairs of cities** (every city to every other city) in a weighted graph — unlike Dijkstra which only does **one source to all**.

---

## Core Idea – Dynamic Programming

> For every pair (i, j), check if going **through an intermediate city k** gives a shorter path than the current known distance.

```
D[i][j] = min(D[i][j],  D[i][k] + D[k][j])
           direct path    go via city k
```

Try every possible **k as a "stepping stone"** between i and j.

---

## Key Data Structure

```java
int[][] D[][];   // D[i][j] = shortest known distance from i to j
                 // (modified in-place, starts as the cost matrix)
```

**Input conventions:**
```
diagonal (i == j)  → 0       (distance to itself)
no direct road     → 999     (acts as infinity)
direct road        → actual weight
```

> This code uses `999` instead of `Integer.MAX_VALUE` to avoid **integer overflow** when adding two "infinities" (`MAX_VALUE + anything` wraps around to negative)

---

## Code Walkthrough

### The entire algorithm is just 4 lines!

```java
for (int k = 0; k < n; k++)        // try each city as intermediate
    for (int i = 0; i < n; i++)     // for every source city
        for (int j = 0; j < n; j++) // for every destination city
            D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
```

### Loop order matters — always **k outermost**
- `k` = the intermediate/relay city being considered
- `i` = source city
- `j` = destination city

After round `k`, `D[i][j]` holds the shortest path using only cities `0..k` as intermediates.

---

## Dry Run Example

4 stations, input cost matrix (999 = no road):

```
      0    1    2    3
0  [  0    3  999    7 ]
1  [  3    0    2  999 ]
2  [999    2    0    1 ]
3  [  7  999    1    0 ]
```

**k=0** (relay through station 0):
- D[1][3] = min(999, D[1][0]+D[0][3]) = min(999, 3+7) = **10**

**k=1** (relay through station 1):
- D[0][2] = min(999, D[0][1]+D[1][2]) = min(999, 3+2) = **5**
- D[2][0] = min(999, D[2][1]+D[1][0]) = min(999, 2+3) = **5**

**k=2** (relay through station 2):
- D[0][3] = min(7, D[0][2]+D[2][3]) = min(7, 5+1) = **6**
- D[1][3] = min(10, D[1][2]+D[2][3]) = min(10, 2+1) = **3**

**k=3** (relay through station 3):
- D[0][1] = min(3, D[0][3]+D[3][1]) = min(3, 6+1) = **3** (no change)

**Final matrix:**
```
      0   1   2   3
0  [  0   3   5   6 ]
1  [  3   0   2   3 ]
2  [  5   2   0   1 ]
3  [  6   3   1   0 ]
```

---

## Floyd vs Dijkstra — Key Differences

| Feature | Dijkstra | Floyd-Warshall |
|---------|----------|----------------|
| Shortest path | One source → all | **All pairs** |
| Approach | Greedy | **Dynamic Programming** |
| Time complexity | O(n²) | **O(n³)** |
| Negative weights | ❌ No | ✅ Yes |
| Code complexity | More lines | **Just 3 nested loops** |
| Infinity value | `Integer.MAX_VALUE` | **999** (avoids overflow) |

---

## Key Points to Remember for Your Exam

| # | Point |
|---|-------|
| 1 | Finds shortest path between **every pair** of nodes |
| 2 | Uses **Dynamic Programming**, not greedy |
| 3 | Triple nested loop — complexity is **O(n³)** |
| 4 | **k must be the outermost loop** — order is critical |
| 5 | `D[i][j] = min(D[i][j], D[i][k] + D[k][j])` is the **relaxation formula** |
| 6 | Uses `999` as infinity to **prevent overflow** on addition |
| 7 | `i == j` diagonal stays **0** (distance to itself) |
| 8 | Matrix is updated **in-place** — no extra array needed |




# 0/1 Knapsack – Dynamic Programming

## What problem does it solve?

Same knapsack problem as before — maximize profit within weight capacity — but now **fractions are NOT allowed**. Each item is either **fully taken (1) or left (0)**.

---

## Why DP and not Greedy?

| | Greedy (Fractional) | DP (0/1) |
|---|---|---|
| Fractions allowed? | ✅ Yes | ❌ No |
| Always optimal? | ✅ Yes | ✅ Yes |
| Approach | Ratio-based | Build table of subproblems |

Greedy **fails** for 0/1 because taking the best ratio item might block a better overall combination.

---

## Key Data Structure — The DP Table

```java
int[][] v = new int[n+1][m+1];
// v[i][j] = max profit using first i items with capacity j
```

- **Rows** = items (0 to n)
- **Columns** = capacity (0 to m)
- **Base case:** row 0 and col 0 are all `0` (no items or no capacity = 0 profit)

---

## Code Walkthrough

### Building the DP Table

```java
for (int i = 0; i <= n; i++) {
    for (int j = 0; j <= m; j++) {

        if (i == 0 || j == 0)
            v[i][j] = 0;                    // base case

        else if (j < w[i])                  // item i is too heavy
            v[i][j] = v[i-1][j];            // skip it, take previous best

        else
            v[i][j] = max(
                v[i-1][j],                  // SKIP item i
                p[i] + v[i-1][j - w[i]]    // TAKE item i
            );
    }
}
```

The core decision at every cell:

```
┌─────────────────────────────────────────────────┐
│  Can item i fit? (j >= w[i])                    │
│                                                 │
│  YES → max(skip it, take it)                    │
│         skip = v[i-1][j]                        │
│         take = p[i] + v[i-1][j - w[i]]         │
│                                                 │
│  NO  → must skip → v[i-1][j]                   │
└─────────────────────────────────────────────────┘
```

---

### Traceback — Which items were selected?

```java
while (n != 0) {
    if (v[n][m] != v[n-1][m]) {  // profit changed → item n was TAKEN
        System.out.print(n + " ");
        m = m - w[n];             // reduce remaining capacity
    }
    n--;                          // move to previous item
}
```

Read the table **bottom-up**. If `v[n][m] != v[n-1][m]`, item `n` contributed to the profit, so it was included.

---

## Dry Run Example

3 items, capacity = **5**

| Item | Weight | Profit |
|------|--------|--------|
| 1    | 2      | 3      |
| 2    | 3      | 4      |
| 3    | 4      | 5      |

**DP Table v[i][j]:**

```
cap→  0   1   2   3   4   5
i=0 [ 0   0   0   0   0   0 ]   ← no items
i=1 [ 0   0   3   3   3   3 ]   ← item1 (w=2,p=3)
i=2 [ 0   0   3   4   4   7 ]   ← item2 (w=3,p=4)
i=3 [ 0   0   3   4   5   7 ]   ← item3 (w=4,p=5)
```

**Optimal profit = v[3][5] = 7**

**Traceback:**
- `v[3][5]=7` vs `v[2][5]=7` → same → item 3 **NOT taken**, n=2, m=5
- `v[2][5]=7` vs `v[1][5]=3` → different → item 2 **TAKEN**, m=5−3=2, n=1
- `v[1][2]=3` vs `v[0][2]=0` → different → item 1 **TAKEN**, m=2−2=0, n=0

**Items selected: 1 and 2** → profit = 3+4 = **7** ✅

---

## Filling one cell manually — v[2][5]

- i=2 (w=3, p=4), j=5
- j >= w[i] → 5 >= 3 ✅
- Skip item 2 → `v[1][5]` = 3
- Take item 2 → `p[2] + v[1][5-3]` = 4 + v[1][2] = 4+3 = **7**
- `max(3, 7)` = **7** ✅

---

## Complexity

| | Value |
|---|---|
| Time | **O(n × m)** |
| Space | **O(n × m)** for the table |

---

## Key Points to Remember for Your Exam

| # | Point |
|---|---|
| 1 | Items indexed from **1 to n** (index 0 = base case row) |
| 2 | `v[i][j]` = best profit with **first i items, capacity j** |
| 3 | If item too heavy (`j < w[i]`) → **always skip** |
| 4 | Otherwise → **max(skip, take)** |
| 5 | "Take" formula = `p[i] + v[i-1][j - w[i]]` |
| 6 | Traceback goes **bottom to top** of the table |
| 7 | `v[n][m] != v[n-1][m]` means item was **included** |
| 8 | Uses `999` / `MAX_VALUE` — **no**, uses actual `0` base case |

---

## Quick Comparison — All 3 Algorithms

| | Greedy Knapsack | DP Knapsack | Floyd | Dijkstra |
|---|---|---|---|---|
| Paradigm | Greedy | **DP** | DP | Greedy |
| Fractions | ✅ | ❌ | — | — |
| Complexity | O(n²) | **O(n×m)** | O(n³) | O(n²) |



# Hamiltonian Circuit – Backtracking

## What problem does it solve?

Find a path that **visits every vertex exactly once** and **returns to the starting vertex**. Used here as a "delivery vehicle route" — visit every city once and return to base.

---

## Core Idea – Backtracking

> Try placing a vertex in the path. If it leads to a dead end, **undo it (backtrack)** and try the next option.

This is different from Greedy/DP — there's no formula for the best choice, so we **try all possibilities** and prune dead ends early.

---

## Key Data Structures

```java
int[][] a[][];          // adjacency matrix: a[i][j]=1 means edge exists
int[] path[];           // current path being built
boolean[] visited[];    // visited[i]=true means vertex i already in path
boolean found;          // flag: did we find at least one circuit?
```

---

## Code Walkthrough

### `main()` — Setup
```java
path[0] = 0;        // always start from vertex 0
visited[0] = true;  // mark source as visited
hamiltonian(1, path, visited);  // fill positions 1 to n-1
```

---

### `hamiltonian(pos, path, visited)` — Recursive Backtracking

#### Base Case — all vertices placed
```java
if (pos == n) {
    if (a[path[n-1]][path[0]] == 1) {  // does last vertex connect back to start?
        found = true;
        printPath(path);               // valid circuit found!
    }
    return;
}
```

#### Recursive Case — try every candidate vertex
```java
for (int v = 1; v < n; v++) {              // v starts at 1 (0 is fixed start)
    if (!visited[v] &&                      // not already in path
        a[path[pos-1]][v] == 1) {           // edge exists from previous vertex
        
        path[pos] = v;                      // CHOOSE: place v at position pos
        visited[v] = true;
        
        hamiltonian(pos + 1, path, visited);// EXPLORE: recurse deeper
        
        visited[v] = false;                 // BACKTRACK: undo the choice
    }
}
```

The three steps — **Choose → Explore → Backtrack** — are the universal pattern for all backtracking problems.

---

## `printPath()` — Print the Circuit
```java
for (int i = 0; i < n; i++)
    System.out.print(path[i] + "->");
System.out.println(path[0]);   // close the loop back to start
```
Output looks like: `0->2->3->1->0`

---

## Dry Run Example

4 vertices, adjacency matrix:
```
   0  1  2  3
0[ 0  1  1  1 ]
1[ 1  0  1  0 ]
2[ 1  1  0  1 ]
3[ 1  0  1  0 ]
```

**Recursive Tree:**
```
Start: path=[0,_,_,_]  visited={0}

pos=1: try v=1 → edge 0→1 ✅
  path=[0,1,_,_]  visited={0,1}
  pos=2: try v=2 → edge 1→2 ✅
    path=[0,1,2,_]  visited={0,1,2}
    pos=3: try v=3 → edge 2→3 ✅
      path=[0,1,2,3]
      pos=4 (==n): edge 3→0? a[3][0]=1 ✅
      ★ CIRCUIT FOUND: 0->1->2->3->0

  pos=2: try v=3 → edge 1→3? a[1][3]=0 ❌ skip

pos=1: try v=2 → edge 0→2 ✅
  path=[0,2,_,_]
  pos=2: try v=1 → edge 2→1 ✅
    path=[0,2,1,_]
    pos=3: try v=3 → edge 1→3? a[1][3]=0 ❌
    DEAD END → backtrack

  pos=2: try v=3 → edge 2→3 ✅
    path=[0,2,3,_]
    pos=3: try v=1 → edge 3→1? a[3][1]=0 ❌
    DEAD END → backtrack

pos=1: try v=3 → edge 0→3 ✅
  ... (continues similarly)
```

---

## Choose → Explore → Backtrack Visual

```
        [0]
       / | \
     [1][2][3]       ← pos=1: try each unvisited neighbour
     /
   [0,1]
    / \
 [0,1,2][0,1,3]     ← pos=2
   /
[0,1,2,3]           ← pos=3
   |
check 3→0?          ← base case
  ✅ print circuit
```

---

## Complexity

| | Value |
|---|---|
| Worst case | **O(n!)** — try all permutations |
| Pruning helps | Skip branches early if no edge exists |

---

## Hamiltonian Circuit vs Hamiltonian Path

| | Circuit | Path |
|---|---|---|
| Returns to start? | ✅ Yes | ❌ No |
| Check at base case | `a[path[n-1]][path[0]]==1` | Just print path |

---

## Key Points to Remember for Your Exam

| # | Point |
|---|---|
| 1 | Uses **Backtracking** — try, explore, undo |
| 2 | Vertex `0` is always the **fixed starting point** |
| 3 | Loop starts at `v=1` — source (0) is **never re-placed** |
| 4 | Two conditions to place vertex: **unvisited** AND **edge exists** |
| 5 | At base case (`pos==n`), check if **last→first edge** exists |
| 6 | `visited[v] = false` is the **backtrack step** — critical! |
| 7 | `found` flag lets you print **"No circuit"** if nothing works |
| 8 | Time complexity is **O(n!)** — exponential |
| 9 | Uses **adjacency matrix**, not adjacency list |




# Sum of Subsets – Backtracking

## What problem does it solve?

Given `n` budget categories with fixed costs, find **all subsets** that add up to **exactly the target budget `d`**. Framed here as event planning — which combination of categories can you fund with a fixed budget?

---

## Core Idea – Backtracking on a Binary Tree

> For each item, make a binary choice — **include it (x[k]=1)** or **exclude it (x[k]=0)** — and prune branches that can't possibly reach the target.

Each level of the tree = one item. Left branch = include, Right branch = exclude.

---

## Key Data Structures

```java
int[] w[];          // weights/budgets of each category
int[] x[];          // x[i]=1 → included, x[i]=0 → excluded
int d;              // target budget
int count;          // number of valid subsets found
String[] categories // names of budget categories
```

### Three parameters of `sum_of_subsets(s, k, rem)`:
```
s   = sum of items included SO FAR
k   = current item index being considered
rem = sum of REMAINING items not yet decided (from k onwards)
```

---

## Code Walkthrough

### Feasibility Check in `main()`
```java
if ((sum < d) || (w[0] > d))
    // No solution possible if:
    // total of all items < target (can never reach d)
    // smallest item > target (even minimum exceeds d)
```

---

### `sum_of_subsets(s, k, rem)`

#### Step 1 — Always try INCLUDING item k (Left branch)
```java
x[k] = 1;
if (s + w[k] == d) {       // exact match → subset found!
    print subset;
}
else if (s + w[k] + w[k+1] <= d) {  // room for more → go deeper
    sum_of_subsets(s + w[k], k+1, rem - w[k]);
}
```

The condition `s + w[k] + w[k+1] <= d` is **pruning** — only go left if at least one more item can still fit.

#### Step 2 — Try EXCLUDING item k (Right branch)
```java
if ((s + rem - w[k] >= d) &&   // enough budget remaining to still reach d
    (s + w[k+1] <= d)) {        // next item alone doesn't overshoot
    x[k] = 0;
    sum_of_subsets(s, k+1, rem - w[k]);
}
```

Two pruning conditions together mean: **"there's still hope even without item k."**

---

## Pruning Rules — The Key to Efficiency

| Condition | Meaning | Action |
|---|---|---|
| `s + w[k] == d` | Exact match | ✅ Print subset |
| `s + w[k] + w[k+1] <= d` | Including k, next item still fits | Go **left** (include) |
| `s + rem - w[k] >= d` | Excluding k, remaining sum still enough | Go **right** (exclude) |
| `s + w[k+1] <= d` | Next item doesn't overshoot alone | Go **right** (exclude) |

---

## Dry Run Example

4 categories, budgets (sorted) = `[1, 2, 3, 4]`, target `d = 5`

```
sum=10, d=5, w[0]=1 (feasible)
Call: sum_of_subsets(0, 0, 10)
```

**Binary Tree:**
```
                    (s=0, rem=10)
                   /              \
           Include w[0]=1        Exclude w[0]=1
           (s=1, rem=9)          (s=0, rem=9)
           /        \            /           \
      Incl w[1]=2  Excl    Incl w[1]=2     Excl
      (s=3,rem=7)           (s=2,rem=7)
       /      \               /      \
  Incl w[2]  Excl        Incl w[2]  Excl
  s=3+3=6>5❌  s=3        s=2+3=5 ✅  ...
              /    \      FOUND!
          Incl w[3] Excl  {2,3}
          s=3+4=7❌   s=3+0=3<5❌

★ Subset 1: {1,2,2} → 1+4=5? ...
```

**All valid subsets for [1,2,3,4], d=5:**
- `{1, 4}` → 1+4 = 5 ✅
- `{2, 3}` → 2+3 = 5 ✅

---

## Why Must Weights Be Sorted (Increasing)?

The pruning conditions **depend on sorted order**:
- `w[k+1]` must be the next smallest for `s + w[k+1] <= d` to be a valid bound
- Without sorting, pruning cuts valid branches → **wrong answers**

---

## Complexity

| | Value |
|---|---|
| Worst case | **O(2ⁿ)** — all subsets |
| With pruning | Much better in practice |

---

## Key Points to Remember for Your Exam

| # | Point |
|---|---|
| 1 | Uses **Backtracking** with a binary include/exclude tree |
| 2 | Weights **must be sorted in increasing order** |
| 3 | `s` = current sum, `rem` = remaining undecided sum |
| 4 | Left branch = **include** (`x[k]=1`), Right = **exclude** (`x[k]=0`) |
| 5 | Two pruning checks prevent going down dead-end branches |
| 6 | `x[k]=0` before right branch is the **backtrack step** |
| 7 | Feasibility pre-check: `sum < d` or `w[0] > d` → no solution |
| 8 | Finds **all** valid subsets, not just one |
| 9 | Time complexity is **O(2ⁿ)** worst case |

---

## Quick Comparison — All Backtracking Algorithms

| | Hamiltonian | Sum of Subsets |
|---|---|---|
| Choice per step | Which vertex next | Include or exclude item |
| Tree type | n-ary (try each vertex) | **Binary** (in or out) |
| Pruning | No edge = prune | Sum bounds = prune |
| Solutions | All circuits | **All valid subsets** |
| Complexity | O(n!) | O(2ⁿ) |



Good luck tomorrow! 🍀
