package rhilenova.allomancy.model;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Tassle
{
    double x;
    double y;
    double z;
    int dir;
    
    Random rand;
    
    double x_off;
    double z_off;
    
    double width;
    final double length = 1.15f;
    
    int counter;
    
    double theta;

    public Tassle(double x, double y, double z, double width, int dir)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dir = dir;
        this.rand = new Random();
        this.counter = 0;
        this.width = width;
        this.theta = 0;
    }
    
    public void render(Entity entity, Tessellator tessellator)
    {
        int rotation_dir = -1;
        EntityLivingBase player = (EntityLivingBase)entity;

        if (player.fallDistance > 0)
        {
            rotation_dir = 8;
        }
        else
        {
            if (player.moveForward > 0)
            {
                rotation_dir = 0;
            }
            else if (player.moveForward < 0)
            {
                rotation_dir = 4;
            }
            if (player.moveStrafing < 0)
            {
                if (rotation_dir == 0)
                {
                    rotation_dir = 1;
                }
                else if (rotation_dir == 4)
                {
                    rotation_dir = 3;
                }
                else
                {
                    rotation_dir = 2;
                }
            }
            else if(player.moveStrafing > 0)
            {
                if (rotation_dir == 0)
                {
                    rotation_dir = 7;
                }
                else if (rotation_dir == 4)
                {
                    rotation_dir = 5;
                }
                else
                {
                    rotation_dir = 6;
                }
            }
        }
        double amount = 0.25;
        if (player.isSprinting() || player.fallDistance > 3) amount *= 2;
        if (dir == 0)drawHorizontalTassle(tessellator, rotation_dir, amount, 0);
        else if (dir == 1)drawRightTassle(tessellator, rotation_dir, amount);
        else if (dir == 2)drawHorizontalTassle(tessellator, rotation_dir, amount, 4);
        else if (dir == 3)drawLeftTassle(tessellator, rotation_dir, amount);
    }
    
    private void drawHorizontalTassle(Tessellator tessellator, int rotation_dir, double amount, int out_dir)
    {
        double top_left[] = {x, y, z};
        double top_right[] = {x + this.width, y, z};
        
        double bottom_left[] = new double[3];
        double bottom_right[] = new double[3];

        double rotation = amount;

        // No movement.
        if (rotation_dir == -1)
        {
            bottom_left[0] = x;
            bottom_left[1] = y + length;
            bottom_left[2] = z;

            bottom_right[0] = x + this.width;
            bottom_right[1] = y + length;
            bottom_right[2] = z;
        }
        // Forward/Backward.
        else if (rotation_dir == 0 || rotation_dir == 4 || rotation_dir == 8)
        {
            if (rotation_dir == 4 || (rotation_dir == 8 && out_dir == 0))
            {
                rotation = -amount;
            }
            bottom_left[0] = x;
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z + (Math.sin(rotation) * length);

            bottom_right[0] = x + this.width;
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z + (Math.sin(rotation) * length);
        }
        // Forward+Right.
        else if (rotation_dir == 1)
        {
            // Rotate back.
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate left.
            rotation = -amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x + this.width + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z - 0.0425;
        }
        // Right.
        else if (rotation_dir == 2)
        {
            rotation = -amount;
            bottom_left[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z + 0.085;

            bottom_right[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z - 0.085;
        }
        // Backward+Right.
        else if (rotation_dir == 3)
        {
            // Rotate forward.
            rotation = -amount;
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate left.
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z - 0.0425;
            
            bottom_right[0] += x + this.width + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + 0.0425;
        }
        // Backward+Left.
        else if (rotation_dir == 5)
        {
            // Rotate forward.
            rotation = -amount;
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate right.
            rotation = amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x + this.width + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z - 0.0425;
        }
        // Left.
        else if (rotation_dir == 6)
        {
            bottom_left[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z - 0.085;

            bottom_right[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z + 0.085;
        }
        // Forward+Left.
        else if (rotation_dir == 7)
        {
            // Rotate back.
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate right.
            rotation = amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z - 0.0425;
            
            bottom_right[0] += x + this.width + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + 0.0425;
        }
        
        tessellator.addVertexWithUV(top_left[0], top_left[1], top_left[2], 0.25f, 0.0f);
        tessellator.addVertexWithUV(top_right[0], top_right[1], top_right[2], 0.25f, 0.125f);
        tessellator.addVertexWithUV(bottom_right[0], bottom_right[1], bottom_right[2], 0.75f, 0.125f);
        tessellator.addVertexWithUV(bottom_left[0], bottom_left[1], bottom_left[2], 0.75f, 0.0f);
    }
    
    private void drawRightTassle(Tessellator tessellator, int rotation_dir, double amount)
    {
        double top_left[] = {x, y, z};
        double top_right[] = {x, y, z + this.width};
        
        double bottom_left[] = new double[3];
        double bottom_right[] = new double[3];

        double rotation = amount;

        // No movement.
        if (rotation_dir == -1)
        {
            
            bottom_left[0] = x;
            bottom_left[1] = y + length;
            bottom_left[2] = z;

            bottom_right[0] = x;
            bottom_right[1] = y + length;
            bottom_right[2] = z + this.width;
        }
        // Forward.
        else if (rotation_dir == 0)
        {            
            bottom_left[0] = x + 0.085;
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z + 0.085 + (Math.sin(rotation) * length);

            bottom_right[0] = x - 0.085;
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z - 0.085 + this.width + (Math.sin(rotation) * length);
        }
        // Forward+Right.
        else if (rotation_dir == 1)
        {
            // Rotate back.
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate left.
            rotation = -amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x - 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        // Right/Left.
        else if (rotation_dir == 2 || rotation_dir == 6 || rotation_dir == 8)
        {
            if (rotation_dir == 2)
            {
                rotation = -amount;
            }
            bottom_left[0] = x - (Math.sin(rotation) * length);
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z;

            bottom_right[0] = x - (Math.sin(rotation) * length);
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z + this.width;
        }
        // Backward+Right.
        else if (rotation_dir == 3)
        {
            // Rotate forward.
            rotation = -amount;
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate left.
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x - 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        // Backward.
        else if (rotation_dir == 4)
        {
            rotation = -amount;
            bottom_left[0] = x - 0.085;
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z + 0.085 + (Math.sin(rotation) * length);

            bottom_right[0] = x + 0.085;
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z - 0.085 + this.width + (Math.sin(rotation) * length);
        }
        // Backward+Left.
        else if (rotation_dir == 5)
        {
            // Rotate forward.
            rotation = -amount;
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate right.
            rotation = amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x - 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        // Left.
        else if (rotation_dir == 6)
        {
            bottom_left[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z - 0.085;

            bottom_right[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z + 0.085;
        }
        // Forward+Left.
        else if (rotation_dir == 7)
        {
            // Rotate back.
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate right.
            rotation = amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x - 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        
        tessellator.addVertexWithUV(top_left[0], top_left[1], top_left[2], 0.25f, 0.0f);
        tessellator.addVertexWithUV(top_right[0], top_right[1], top_right[2], 0.25f, 0.125f);
        tessellator.addVertexWithUV(bottom_right[0], bottom_right[1], bottom_right[2], 0.75f, 0.125f);
        tessellator.addVertexWithUV(bottom_left[0], bottom_left[1], bottom_left[2], 0.75f, 0.0f);
    }
    
    private void drawLeftTassle(Tessellator tessellator, int rotation_dir, double amount)
    {
        double top_left[] = {x, y, z};
        double top_right[] = {x, y, z + this.width};
        
        double bottom_left[] = new double[3];
        double bottom_right[] = new double[3];

        double rotation = amount;

        // No movement.
        if (rotation_dir == -1)
        {
            
            bottom_left[0] = x;
            bottom_left[1] = y + length;
            bottom_left[2] = z;

            bottom_right[0] = x;
            bottom_right[1] = y + length;
            bottom_right[2] = z + this.width;
        }
        // Forward.
        else if (rotation_dir == 0)
        {            
            bottom_left[0] = x - 0.085;
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z + 0.085 + (Math.sin(rotation) * length);

            bottom_right[0] = x + 0.085;
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z - 0.085 + this.width + (Math.sin(rotation) * length);
        }
        // Forward+Right.
        else if (rotation_dir == 1)
        {
            // Rotate back.
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate left.
            rotation = -amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x - 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        // Right/Left.
        else if (rotation_dir == 2 || rotation_dir == 6 || rotation_dir == 8)
        {
            if (rotation_dir == 2 || rotation_dir == 8)
            {
                rotation = -amount;
            }
            bottom_left[0] = x - (Math.sin(rotation) * length);
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z;

            bottom_right[0] = x - (Math.sin(rotation) * length);
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z + this.width;
        }
        // Backward+Right.
        else if (rotation_dir == 3)
        {
            // Rotate forward.
            rotation = -amount;
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate left.
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x - 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        // Backward.
        else if (rotation_dir == 4)
        {
            rotation = -amount;
            bottom_left[0] = x + 0.085;
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z + 0.085 + (Math.sin(rotation) * length);

            bottom_right[0] = x - 0.085;
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z - 0.085 + this.width + (Math.sin(rotation) * length);
        }
        // Backward+Left.
        else if (rotation_dir == 5)
        {
            // Rotate forward.
            rotation = -amount;
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate right.
            rotation = amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x + 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x - 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        // Left.
        else if (rotation_dir == 6)
        {
            bottom_left[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_left[1] = y + (Math.cos(rotation) * length);
            bottom_left[2] = z - 0.085;

            bottom_right[0] = x + 0.085 - (Math.sin(rotation) * length);
            bottom_right[1] = y + (Math.cos(rotation) * length);
            bottom_right[2] = z + 0.085;
        }
        // Forward+Left.
        else if (rotation_dir == 7)
        {
            // Rotate back.
            bottom_left[1] = (Math.cos(rotation) * length);
            bottom_left[2] = (Math.sin(rotation) * length);

            bottom_right[1] = (Math.cos(rotation) * length);
            bottom_right[2] = (Math.sin(rotation) * length);
            
            // Rotate right.
            rotation = amount;
            bottom_left[0] = -(Math.sin(rotation) * bottom_left[1]);
            bottom_left[1] = (Math.cos(rotation) * bottom_left[1]);

            bottom_right[0] = -(Math.sin(rotation) * bottom_right[1]);
            bottom_right[1] = (Math.cos(rotation) * bottom_right[1]);
            
            // Add world offset and rotation.
            bottom_left[0] += x - 0.0425;
            bottom_left[1] += y;
            bottom_left[2] += z + 0.0425;
            
            bottom_right[0] += x + 0.0425;
            bottom_right[1] += y;
            bottom_right[2] += z + this.width + 0.0425;
        }
        
        tessellator.addVertexWithUV(top_left[0], top_left[1], top_left[2], 0.25f, 0.0f);
        tessellator.addVertexWithUV(top_right[0], top_right[1], top_right[2], 0.25f, 0.125f);
        tessellator.addVertexWithUV(bottom_right[0], bottom_right[1], bottom_right[2], 0.75f, 0.125f);
        tessellator.addVertexWithUV(bottom_left[0], bottom_left[1], bottom_left[2], 0.75f, 0.0f);
    }
}
