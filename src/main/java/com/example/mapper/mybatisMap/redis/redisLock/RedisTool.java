package com.example.mapper.mybatisMap.redis.redisLock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        /**
         * https://www.cnblogs.com/linjiqin/p/8003838.html
         * key 锁，requestId sessiond,当key不存在时，我们进行set操作；若key已经存在，则不做任何操作,过期时间。
         * 首先，set()加入了NX参数，可以保证如果已有key存在，则函数不会调用成功，也就是只有一个客户端能持有锁，
         * 满足互斥性。其次，由于我们对锁设置了过期时间，即使锁的持有者后续发生崩溃而没有解锁，锁也会因为到了
         * 过期时间而自动解锁（即key被删除），不会发生死锁。最后，因为我们将value赋值为requestId，代表加锁的客
         * 户端请求标识，那么在客户端在解锁的时候就可以进行校验是否是同一个客户端。由于我们只考虑Redis单机部署
         * 的场景，所以容错性我们暂不考虑。
         */
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        /**
         * 我们将Lua代码传到jedis.eval()方法里，并使参数KEYS[1]赋值为lockKey，ARGV[1]赋值为requestId。eval()方法是
         * 将Lua代码交给Redis服务端执行。那么这段Lua代码的功能是什么呢？其实很简单，首先获取锁对应的value值，检查
         * 是否与requestId相等，如果相等则删除锁（解锁）。
         */
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
