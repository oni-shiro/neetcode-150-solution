/**
    logic is => for every node (if not leaf) swap values between left and right
    return to parent
 */
class InvertTree {

    private void helper(TreeNode root){
        if(root == null){
            return;
        }
        // if leaf node return
        if(root.left == null && root.right == null){
            return;
        }

        if(root.left != null){
            helper(root.left);
        }
        if(root.right != null){
            helper(root.right);
        }

        TreeNode temp = null;
        if(root.left != null){
            temp = root.left;
        }

        if(root.right != null){
            root.left = root.right;
        }else{
            root.left = null;
        }

        root.right = temp;

    }

    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
    }
}