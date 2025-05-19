import asyncio

async def worker(queue, worker_id):
    while not queue.empty():
        n = await queue.get()
        result = sum(range(n + 1))
        print(f"Corutina {worker_id} a calculat suma pana la {n}: {result}")
        queue.task_done()

async def main():
    queue = asyncio.Queue()


    for n in [5, 6, 7, 8]:
        await queue.put(n)


    tasks = [asyncio.create_task(worker(queue, i)) for i in range(4)]

    await queue.join()


    for task in tasks:
        await task

    print("Toate corutinele au terminat calculele.")

asyncio.run(main())
