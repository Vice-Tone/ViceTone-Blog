package com.hohai.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hohai.dao.BlogSearchDao;
import com.hohai.entity.Blog;
import com.hohai.entity.TableMap;
import com.hohai.entity.Type;
import com.hohai.entity.User;
import com.hohai.queryvo.BlogQuery;
import com.hohai.queryvo.EditBlog;
import com.hohai.queryvo.SearchBlog;
import com.hohai.service.BlogService;
import com.hohai.service.TypeService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/24 9:38 上午
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Resource
    private BlogSearchDao blogSearchDao;


    /**
     * 分页查询所有博客，并显示博客的分类
     *
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<BlogQuery> list = blogService.getAllBlogs();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    /**
     * 跳转到新增博客页面
     *
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        //????????????????????????????为什么要new一个Blog
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    /**
     * 新增博客
     *
     * @return
     */
    @PostMapping("/blogs")
    public String save(Blog blog, RedirectAttributes redirectAttributes, HttpSession session) {
        //还有user，type等属性没赋值
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int statNum = blogService.saveBlog(blog);
        //statNum为1则表示保存成功
        //为0则表示保存失败
        if (statNum == 1) {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        } else if (statNum == 0) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/blogs";
    }


    /**
     * 根据id删除博客
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


    /**
     * 先根据id把博客查询出来，然后显示在页面中
     *
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String edit(@PathVariable Long id, Model model) {
        EditBlog editBlog = blogService.findBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog", editBlog);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }

    /**
     * 更新博客
     *
     * @param editBlog
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/blogs/{id}")
    public String update(@Valid EditBlog editBlog, RedirectAttributes redirectAttributes) {
        int statNum = blogService.updateBlog(editBlog);
        if (statNum == 0) {
            redirectAttributes.addFlashAttribute("message", "更新失败");
        } else if (statNum == 1) {
            redirectAttributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/blogs";
    }


    /**
     * 根据分类id和title搜索博客
     *
     * @param searchBlog
     * @param model
     * @param pageNum
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        List<BlogQuery> list = blogService.searchBlog(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs::blogList";
    }


    @RequestMapping("/blogImageUpload")
    @ResponseBody
    public TableMap blogImageUpload(@RequestParam("editormd-image-file") MultipartFile file,
                                    HttpServletRequest request, HttpServletResponse response) {
        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis() + suffix;

        String path = request.getSession().getServletContext().getRealPath("/images/blogImage/");
        System.out.println(path);

        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TableMap(1, "upload success!", path + fileName);
    }

    @PostMapping("/blogs/searchByES")
    @ResponseBody
    public List<Blog> searchByES(String keyword) {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        Iterable<Blog> blogs = blogSearchDao.search(termQueryBuilder);
        ArrayList<Blog> res = new ArrayList<>();
        for (Blog blog : blogs) {
            res.add(blog);
        }
        TermQueryBuilder termQueryBuilder1 = QueryBuilders.termQuery("content", keyword);
        Iterable<Blog> blogs1 = blogSearchDao.search(termQueryBuilder1);
        for (Blog blog : blogs1) {
            res.add(blog);
        }
        return res;
    }

}
