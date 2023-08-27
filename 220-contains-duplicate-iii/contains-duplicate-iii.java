class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        if (n<=1) return false;
        if (t<0) return false;
        
        // take care when t == 0
        long width = (long)t+1;
        
        Map<Long,Long> map = new HashMap<>();
        for (int i = 0; i < n; i ++)
        {
            long bucketId = getBucketId((long)nums[i],width);
            if (map.containsKey(bucketId))
            {
                return true;
            }
            if (map.containsKey(bucketId-1l) && Math.abs(map.get(bucketId-1)-nums[i])<width)
            {
                return true;
            }
            if (map.containsKey(bucketId+1l) && Math.abs(map.get(bucketId+1)-nums[i])<width)
            {
                return true;
            }
            map.put(bucketId,(long)nums[i]);
            if (i-k>=0)
            {
                long oldBucketId = getBucketId((long)nums[i-k],width);
                map.remove(oldBucketId);
            }
        }
        
        return false;
    }
    
    private long getBucketId(long val, long width)
    {
        return val < 0 ? (val+1l)/width-1l : val/width;
    }
}